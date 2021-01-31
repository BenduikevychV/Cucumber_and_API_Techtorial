package API;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
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

public class APIPractisagain {

    @Test
    public void test1() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
//      https://api.got.show/api/map/characters
        uriBuilder.setScheme("https").setHost("api.got.show").setPath("api/map/characters");

        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept","application/json");

        HttpResponse response = client.execute(get);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String,Object> parseResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String,Object>>() {});

        List<Map<String,Object>> dataValues =( List<Map<String,Object>>) parseResponse.get("data");

        Map<String,Object> firstHouse = dataValues.get(0);

       for(int i =0; i<dataValues.size();i++){
           System.out.println(dataValues.get(i).get("house"));
       }


    }

    @Test
    public void test2() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
//      https://reqres.in/api/users
        uriBuilder.setScheme("https").setHost("reqres.in").setPath("api/users").setCustomQuery("per_page=12");

        HttpGet get = new HttpGet(uriBuilder.build());

        HttpResponse response = client.execute(get);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String,Object> jsObject = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String,Object>>() {});

        List<Map<String, Object>> users = (List<Map<String, Object>>)jsObject.get("data");
        Assert.assertEquals(users.size(),jsObject.get("per_page"));

        int sum = 0;
        for (Map<String, Object> user : users){
            sum+= (int)user.get("id");
        }
        System.out.println(sum);
    }

    @Test
    public void test3() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
//      https://reqres.in/api/users
        uriBuilder.setScheme("https").setHost("reqres.in").setPath("api/users").setCustomQuery("per_page=12");

        HttpGet get = new HttpGet(uriBuilder.build());

        HttpResponse response = client.execute(get);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String,Object> jsObject = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String,Object>>() {});

        List<Map<String, Object>> users = (List<Map<String, Object>>)jsObject.get("data");

        Assert.assertEquals(users.size(),jsObject.get("per_page"));

        System.out.println(users.size());


    }


    @Test
    public void test4() throws URISyntaxException, IOException {

        int limit = 100;
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
    //https://itunes.apple.com/search?term=linkinpark

        uriBuilder.setScheme("https").setHost("itunes.apple.com").setPath("search").setCustomQuery("term=linkinpark"+"&limit="+limit);

        HttpGet get = new HttpGet(uriBuilder.build());


        HttpResponse response = client.execute(get);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String,Object> jsObject = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String,Object>>() {});

        List<Map<String, Object>> songs = (List<Map<String, Object>>)jsObject.get("results");

        Assert.assertEquals(songs.size(),jsObject.get("resultCount"));

        System.out.println(songs.size());


        for( Map<String, Object> artName : songs){

            Assert.assertTrue(artName.get("artistName").toString().toUpperCase().contains("LINKIN PARK"));
        }
    }



}
