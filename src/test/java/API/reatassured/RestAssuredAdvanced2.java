package API.reatassured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;  // --->wildcard

public class RestAssuredAdvanced2 {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;

    @Before
    public void setup() {
        RestAssured.baseURI = "https://api.football-data.org";
        RestAssured.basePath = "v2/competitions/2000/scorers";
        requestSpecification = new RequestSpecBuilder().setAccept(ContentType.JSON).addHeader("X-Auth-Token", "72bd7f61c55842bd88ee905ed35f15db").build();
        responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();



    }

    @Test
    public void adv1() {

        Response  response = given().spec(requestSpecification).when().get().then().spec(responseSpecification).extract().response();

        Map<String, ?> map = response.as(Map.class);

        System.out.println(map.get("count"));

        List<Map<String,Object>> scorers =(List<Map<String,Object>>) map.get("scorers");
        System.out.println(scorers.get(1).get("team"));
    }

}
