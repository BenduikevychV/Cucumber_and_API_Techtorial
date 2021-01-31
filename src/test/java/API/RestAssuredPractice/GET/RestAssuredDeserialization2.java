package API.RestAssuredPractice.GET;

import API.pojo.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredDeserialization2 {


    @Test
    public void test1(){

        UserPojo parseUser = given().header("accept", ContentType.JSON).when()
                .request("GET","https://reqres.in/api/users/2")
                .then().statusCode(200).contentType(ContentType.JSON).extract().as(UserPojo.class);


        Assert.assertEquals("janet.weaver@reqres.in",parseUser.getData().getEmail());

    }


    @Test
    public void test2(){

        // Prerequisite
        RequestSpecification requestSpecification =  given().header("accept",ContentType.JSON);
        // Action -> getting response
        Response response =requestSpecification.when().get("https://reqres.in/api/users/2");

        // validation
        ValidatableResponse validatableResponse = response.then().statusCode(200).contentType(ContentType.JSON);

        // deserialization
        UserPojo deserelizedUser = validatableResponse.log().all().extract().as(UserPojo.class);

        // verification
        Assert.assertEquals("Janet",deserelizedUser.getData().getFirst_name());
    }

}
