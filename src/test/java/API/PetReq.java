package API;

import API.pojo.PetPojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class PetReq {


    @Test
    public void getPet() throws IOException, URISyntaxException {

        //https://petstore.swagger.io/v2/pet
        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("petstore.swagger.io").setPath("v2/pet/707");

        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(get);

        ObjectMapper objectMapper = new ObjectMapper();


        PetPojo deserializedObject = objectMapper.readValue(response.getEntity().getContent(), PetPojo.class);

        System.out.println(deserializedObject.getId());
        System.out.println(deserializedObject.getName());
        System.out.println(deserializedObject.getPhotoUrls());
        System.out.println(deserializedObject.getStatus());

    }

}
