package StepDefinitions.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import static Utils.PayloadUtil.getPetPayload;

public class APIStepDefs {


    HttpClient client;
    HttpResponse response;
    int id = 777;
    String name = "Norris";
    String status = "Alive";

    @When("user creates a pet with id, name, status")
    public void user_creates_a_pet_with_id_name_status() throws IOException, URISyntaxException {

        client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        //https://petstore.swagger.io/v2/pet
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet");

        HttpPost post = new HttpPost(uriBuilder.build());

        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");


        System.out.println("Building request body");
        HttpEntity entity = new StringEntity(getPetPayload(id, name, status));
        post.setEntity(entity);

        System.out.println("Started POST method execution");
        response = client.execute(post);
        System.out.println("Finished POST method execution");
    }


    @Then("the status code is OK")
    public void the_status_code_is_OK() {
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }


    @Then("pet with id, name, status is created")
    public void pet_with_id_name_status_is_created() throws IOException {

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
    }

}
