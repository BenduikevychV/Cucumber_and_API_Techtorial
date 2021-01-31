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
import java.util.Map;

public class DeserializationPractice {

    @Test
    public void deserialization1() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        // https://petstore.swagger.io/v2/pet/1
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet/1");

        HttpGet get = new HttpGet(uriBuilder.build());

        get.setHeader("Accept","application/json");

        HttpResponse response = client.execute(get);

        if(response.getStatusLine().getStatusCode()!=HttpStatus.SC_OK){
            Assert.fail();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> deserializaitionResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String,Object>>() {});

        System.out.println(deserializaitionResponse.get("id"));
        System.out.println(deserializaitionResponse.get("category"));
        System.out.println(deserializaitionResponse.get("name"));
        System.out.println(deserializaitionResponse.get("status"));

        Map<String,Object> category = (Map<String, Object>) deserializaitionResponse.get("category");
        System.out.println("category id is: "+category.get("id"));
        System.out.println("category name is: "+category.get("name"));

       Object tags = (Object) deserializaitionResponse.get("tags");


    }
}
