package API.JiraAPI;

import Utils.PayloadUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
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
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VerifyStories {

    ObjectMapper objectMapper = new ObjectMapper();
    HttpClient client;
    URIBuilder uriBuilder;

    @Before
    public void setup() throws URISyntaxException, IOException {
        client = HttpClientBuilder.create().build();
        uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http").setHost("localhost:8080").setPath("rest/api/2/search")
        .setCustomQuery("jql=assignee=Roman");


    }


    @Test
    public void verifyNumberOfStories() throws URISyntaxException, IOException {

        //  http://localhost:8080/rest/api/2/search?jql=assignee=Roman

        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");
        get.setHeader("Content-Type", "application/json");
        get.setHeader("Cookie",PayloadUtil.getJsessionCookie());
        
        HttpResponse response = client.execute(get);

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        Map<String,Object> parseResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String,Object>>() {
        });

        List<Map<String,Object>> issueList =(List<Map<String,Object>> ) parseResponse.get("issues");

        Assert.assertEquals(9,issueList.size());
        Set<String> issueKeys = new HashSet<>();

        for (Map<String, Object> map : issueList){
            issueKeys.add((String) map.get("key"));
        }
        Assert.assertEquals(9, issueKeys.size());

    }

    @Test
    public void advDeser() throws IOException, URISyntaxException {

        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");
        get.setHeader("Content-Type", "application/json");
        get.setHeader("Cookie",PayloadUtil.getJsessionCookie());

        HttpResponse response = client.execute(get);

        JsonNode parseResponse = objectMapper.readTree(response.getEntity().getContent());

        System.out.println(parseResponse.get("total"));


        System.out.println(parseResponse.get("issues").get(1).get("key"));

        String expectedResult = "US-173";

        System.out.println(parseResponse.get("issues").get(0).get("fields").get("issuetype").get("description"));
    }

}
