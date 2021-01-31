package API.JiraAPI;

import API.JiraAPI.JiraPojo.JiraPojo;
import API.JiraAPI.JiraPojo.JiraPojoCookie;
import Utils.PayloadUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class CookieAuth {


    @Test
    public void test1() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        //  http://localhost:8080/rest/auth/1/session
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http").setHost("localhost:8080").setPath("rest/auth/1/session");

        HttpPost post = new HttpPost(uriBuilder.build());
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-Type", "application/json");


        JiraSetBody jiraSetBody = new JiraSetBody("Volodymyr", "StepanSraka");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("target/jira1.json"), jiraSetBody);

        String jira1Payload = PayloadUtil.generateStringFromResource("target/jira1.json");

        HttpEntity entity = new StringEntity(jira1Payload);
        post.setEntity(entity);

        HttpResponse response = client.execute(post);

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        JiraPojoCookie jiraPojoCookie = objectMapper.readValue(response.getEntity().getContent(), JiraPojoCookie.class);

        System.out.println(jiraPojoCookie.getSession().get("name"));
        System.out.println(jiraPojoCookie.getSession().get("value"));


    }

    @Test
    public void test2() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        // http://localhost:8080/rest/api/2/issue
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http").setHost("localhost:8080").setPath("rest/api/2/issue");

        HttpPost post = new HttpPost(uriBuilder.build());
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Cookie", PayloadUtil.getJsessionCookie());

        HttpEntity entity = new StringEntity(PayloadUtil.getJiraIssuePayload("Creating a bug from API",
                "Creating a bug using back-ind API call, and vrify in UI",
                "Bug"));
        post.setEntity(entity);

        HttpResponse response = client.execute(post);

        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusLine().getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String,String> parseResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String,String>>() {});

        for (String key : parseResponse.keySet()){
//            System.out.println(key+": "+parseResponse.get(key));
            System.out.printf("%s: %s\n",key, parseResponse.get(key));
        }

    }



}
