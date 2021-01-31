package StepDefinitions.api;

import API.pojo.PetPojo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class SwaggerDeserialize {


    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Given("accept header is set to {string}")
    public void acceptHeaderIsSetTo(String requestHeader) {
         requestSpecification = given().header("accept",requestHeader );
    }

    @When("the user execute {string} request")
    public void theUserExecuteGETRequest(String requestType) {
        response = requestSpecification.request(requestType, "https://petstore.swagger.io/v2/pet/5");
    }

    @Then("the status code is {int}")
    public void the_status_code_is_OK(int statusCode) {
        Assert.assertEquals(statusCode,response.statusCode());
    }

    @Then("contentType header is {string}")
    public void contentTypeHeaderIs(String responseHeader) {
         validatableResponse = response.then().contentType(responseHeader);
    }

    @Then("user verified {int} {string} {int} size")
    public void userVerifiedDoggieSize(int id,String name, int tagsSize) {

        PetPojo deserilizedUser = validatableResponse.extract().as(PetPojo.class);

        Assert.assertEquals(name,deserilizedUser.getName());
        Assert.assertEquals(id,deserilizedUser.getId());
        Assert.assertEquals(tagsSize,deserilizedUser.getTags().size());


    }




}
