package API;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import static Utils.PayloadUtil.getPetPayload;

public class POSTPractice2 {

    @Test
    public void test1() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
//https://petstore.swagger.io/v2/pet
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet");
        // uriBuilder.setCustomQuery("");

        HttpPost post = new HttpPost(uriBuilder.build());

        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");

        int id = 777;
        String name = "Norris";
        String status = "Alive";

        System.out.println("Building request body");
        HttpEntity entity = new StringEntity(getPetPayload(id, name, status));
        post.setEntity(entity);

        System.out.println("Started POST method execution");
        HttpResponse response = client.execute(post);
        System.out.println("Finished POST method execution");

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        System.out.println("Mapping response body with Java object");
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> parseResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });


        int actualId = (int) parseResponse.get("id");
        String actualName = parseResponse.get("name").toString();
        String actualStatus = parseResponse.get("status").toString();

        Assert.assertEquals(name, actualName);
        Assert.assertEquals(id, actualId);
        Assert.assertEquals(status, actualStatus);


        // GET

        uriBuilder.setPath("v2/pet/" + id);

        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");

        System.out.println("Started POST method execution");
        response = client.execute(get);
        System.out.println("Finished POST method execution");

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        System.out.println("Deserialization response");
        parseResponse = objectMapper.readValue(response.getEntity().getContent()
                , new TypeReference<Map<String, Object>>() {
                });

        Assert.assertEquals(id, parseResponse.get("id"));
        Assert.assertEquals(name, parseResponse.get("name"));
        Assert.assertEquals(status, parseResponse.get("status"));




    }
}
