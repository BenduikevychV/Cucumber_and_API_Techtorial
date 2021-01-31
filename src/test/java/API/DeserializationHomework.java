package API;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class DeserializationHomework {

    @Test
    public void test1() throws URISyntaxException, IOException {

//        https://api.got.show/api/map/characters/byId/5cc0743504e71a0010b852d9
        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("api.got.show");
        uriBuilder.setPath("api/map/characters/byId/5cc0743504e71a0010b852d9");

        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept","application/json");

        HttpResponse response = client.execute(get);

        Assert.assertEquals("response didn't work",HttpStatus.SC_OK,response.getStatusLine().getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String,Object> deserializationValue = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String,Object>>() {});

        System.out.println(deserializationValue.get("message"));

        Map<String,Object> data = (Map<String,Object>) deserializationValue.get("data");
        System.out.println(data.get("_id"));
        System.out.println(data.get("dateOfBirth"));
        System.out.println(data.get("imageLink"));
        System.out.println(data.get("male"));
        System.out.println(data.get("culture"));
        System.out.println(data.get("house"));
        System.out.println(data.get("slug"));
        System.out.println(data.get("name"));
        System.out.println(data.get("hasPath"));
        System.out.println(data.get("createdAt"));
        System.out.println(data.get("updatedAt"));
        System.out.println(data.get("__v"));

        List<String> titles = (List<String>)data.get("titles");
        for (String title : titles){
            System.out.println(title);
        }

        List<String> books = (List<String>)data.get("books");
        for (String book : books){
            System.out.println(book);
        }

    }



}
