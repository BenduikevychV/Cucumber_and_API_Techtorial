package API;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.http.Header;
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

public class DeserializationIntro {


    @Test
    public void deserialization1() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet/1");
        ;
        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(get);
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            Assert.fail();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> deserializationResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });
        System.out.println("id is: " + deserializationResponse.get("id"));
        System.out.println("name is: " + deserializationResponse.get("name"));
        System.out.println("status is: " + deserializationResponse.get("status"));

        Map<String, Object> category = (Map<String, Object>) deserializationResponse.get("category");

        System.out.println(category);

        System.out.println(category.get("id"));

        System.out.println(category.get("name"));

        Object photoUrls = deserializationResponse.get("photoUrls");
        System.out.println(photoUrls);


    }


    @Test
    public void deserialization2() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("reqres.in");
        uriBuilder.setPath("api/users/2");

        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(get);
        if (response.getStatusLine().getStatusCode() != 200) {
            Assert.fail();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> deserializationResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });
        Map<String, String> ad = (Map<String, String>) deserializationResponse.get("ad");
        System.out.println(ad.get("company"));

        System.out.println(ad.get("url"));
        System.out.println(ad.get("text"));
    }


    @Test
    public void deserialization3() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();

        // https://petstore.swagger.io/v2/pet/7
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet/1");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(httpGet);

        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {

            Assert.fail();
        }

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> deserilizedResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        System.out.println(deserilizedResponse.get("id"));
        System.out.println(deserilizedResponse.get("category"));
        System.out.println(deserilizedResponse.get("name"));
        System.out.println(deserilizedResponse.get("tags"));
        System.out.println(deserilizedResponse.get("status"));
        System.out.println(deserilizedResponse.get("photoUrls"));

        Map<String, Object> category = (Map<String, Object>) deserilizedResponse.get("category");
        System.out.println(category.get("name"));
        System.out.println(category.get("id"));



    }

    @Test
    public void chuckNorris() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("api.chucknorris.io");
        uriBuilder.setPath("jokes/random");

        HttpGet get = new HttpGet(uriBuilder.build());

        get.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(get);

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> chuckSays = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });
        System.out.println("Value of value is : " + chuckSays.get("value"));

    }

    @Test
    public void donalFrump() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http");
        uriBuilder.setHost("tronalddump.io");
        uriBuilder.setPath("random/quote");
        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(get);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> frump = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });
        Map<String, Object> links = (Map<String, Object>) frump.get("_links");
        System.out.println(links.get("self"));
    }

}
