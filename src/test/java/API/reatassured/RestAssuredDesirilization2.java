package API.reatassured;

import API.pojo.BBCPojo;
import API.pojo.UserPojo;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RestAssuredDesirilization2 {


    @Test
    public void deser() {

        UserPojo parseUser = given().header("accept", ContentType.JSON).when()
                .request("GET", "https://reqres.in/api/users/2")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .extract().as(UserPojo.class);

        Assert.assertEquals("janet.weaver@reqres.in", parseUser.getData().getEmail());


    }
    @Test
    public void deser2(){

        //PreRequestSuite
        RequestSpecification requestSpecification = given().header("accept",ContentType.JSON);
        //Action -> getting response
        Response response = requestSpecification.when().get( "https://reqres.in/api/users/2");

        //Validation1
        ValidatableResponse validatableResponse = response.then().and().contentType(ContentType.JSON);

        //Deserialization
        UserPojo deserilizedUser = validatableResponse.log().all().extract().as(UserPojo.class);
        //Validation2
        Assert.assertEquals("Janet",deserilizedUser.getData().getFirst_name());
    }



}
