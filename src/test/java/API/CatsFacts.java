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

public class CatsFacts {

    @Test
    public void countCatFacts() throws URISyntaxException, IOException {

        //http://cat-fact.herokuapp.com/facts
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http");
        uriBuilder.setHost("cat-fact.herokuapp.com");
        uriBuilder.setPath("facts");
        ;
        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(get);

        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            Assert.fail();
        }

        //Deserialization

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, List> parseResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, List>>() {
                });


        System.out.println(parseResponse.get("all").size());

    }

    @Test
    public void test2() throws URISyntaxException, IOException {

        // http://cat-fact.herokuapp.com/facts
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http");
        uriBuilder.setHost("cat-fact.herokuapp.com");
        uriBuilder.setPath("facts");
        ;
        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(get);

        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            Assert.fail();
        }

        //Deserialization

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, List<Map<String, Object>>> parseResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, List<Map<String, Object>>>>() {
                });


        System.out.println(parseResponse.get("all").get(0).get("text"));


        for (Map<String, Object> all : parseResponse.get("all")) {

            if (all.get("text").toString().contains("cat")) {
                System.out.println(all.get("_id"));

            }
        }

        System.out.println("=============");

        for (int i = 0; i < parseResponse.get("all").size(); i++) {

            if (!parseResponse.get("all").get(i).get("text").toString().contains("cat")) {
                System.out.println(parseResponse.get("all").get(i).get("text"));
                System.out.println("Index number is: --->>> " + i);
            }
        }


        List<Map<String, Object>> mapList = parseResponse.get("all");

        for (Map<String, Object> map : mapList) {
            System.out.println(map.get("text"));
            Map<String, Object> userInfo = (Map<String, Object>) map.get("user");

            Map<String,String> firstLastName =(Map<String,String>) userInfo.get("name");
            System.out.println(firstLastName.get("first"));
            System.out.println(firstLastName.get("last"));
        }


    }

    @Test
    public void test3() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("reqres.in");
        uriBuilder.setPath("api/users/");
//        https://reqres.in/api/users/
        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(get);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String,Object> userInfo = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        System.out.println(userInfo.get("page"));
        System.out.println(userInfo.get("per_page"));
        System.out.println(userInfo.get("total"));
        System.out.println(userInfo.get("total_pages"));
//        System.out.println(userInfo.get("data"));

        List<Map<String,Object>> data =  (List<Map<String,Object>>)userInfo.get("data");

        System.out.println(data.size());

        for(int i=0; i<data.size(); i++){
            System.out.println(data.get(i).get("first_name"));
        }
    }


}
