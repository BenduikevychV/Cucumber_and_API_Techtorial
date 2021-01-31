package API;

import API.pojo.PetPojo;
import API.pojo.UserPojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import API.pojo.Ad;
import API.pojo.Data;


import java.io.IOException;
import java.net.URISyntaxException;

public class UserCall {

    @Test
    public void getUser() throws URISyntaxException, IOException {


        //https://reqres.in/api/users/7
        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("reqres.in").setPath("api/users/7");

        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(get);

        ObjectMapper objectMapper = new ObjectMapper();

        UserPojo deserializedObject = objectMapper.readValue(response.getEntity().getContent(), UserPojo.class);


        System.out.println(deserializedObject.getData().getFirst_name());
        System.out.println(deserializedObject.getData().getLast_name());
        System.out.println(deserializedObject.getAd().getCompany());
    }

}
