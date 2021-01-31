package API.RahulPractice;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Basic {
    public static void main(String[] args) {

        RestAssured.baseURI="https://rahulshettyacademy.com";
//        Response response = given().log().all().queryParam("key","qaclick123").header("Content-Type", ContentType.JSON)
//                .body(Payload.addPlace())
//                .when().post("maps/api/place/add/json")
//                .then().assertThat().statusCode(200).body("scope",equalTo("APP"))
//                .header("server","Apache/2.4.18 (Ubuntu)").extract().response();
//
//        JsonPath jsonPath = response.jsonPath();
//        String str = jsonPath.getString("reference");
//        System.out.println("value of string "+str);
        // POST
        String response = given().log().all().queryParam("key","qaclick123").header("Content-Type", ContentType.JSON)
                .body(Payload.addPlace())
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope",equalTo("APP"))
                .header("server","Apache/2.4.18 (Ubuntu)").extract().response().asString();

        System.out.println(response);

        JsonPath js = new JsonPath(response);

        String place_id = js.getString("place_id");
        String newAddress = "Summer Walk, Africa";
        System.out.println(place_id);

        // PUT
        given().log().all().queryParam("key","qaclick123").header("Content-Type",ContentType.JSON)
                .body("{\n" +
                        "\"place_id\":\""+place_id+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));

        // GET
        String getPlaceResponse = given().log().all().queryParam("key","qaclick123").queryParam("place_id",place_id)
                .when().get("maps/api/place/get/json")
                .then().assertThat().statusCode(200).extract().response().asString();

        JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);

        Assert.assertEquals(actualAddress,"Summer Walk, Africa");

    }
}
