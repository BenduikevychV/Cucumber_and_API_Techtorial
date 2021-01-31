package API.reatassured;

import API.pojo.PetPojo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredPost {

    public static final String NAME = "name";
    public static final String STATUS = "status";

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @Before
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";

        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON).build();

        responseSpecification = new ResponseSpecBuilder().log(LogDetail.BODY)
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
    }

    @Test
    public void createPet() {

        File petPayloadFile = new File("target/pet.json");

        given().spec(requestSpecification).body(petPayloadFile)
                .when().post()
                .then().spec(responseSpecification)
                .body(NAME, Matchers.equalTo("Chuck Norris"))
                .and().body(STATUS, Matchers.equalTo("gone"));


    }

    @Test
    public void createPet2() {
        PetPojo petPojo = new PetPojo("Hatiko", "gone", 1077);


        given().spec(requestSpecification).body(petPojo)
                .when().post()
                .then().assertThat().spec(responseSpecification)
                .and().body(NAME, Matchers.is(petPojo.getName()))
                .and().body(STATUS, Matchers.is(petPojo.getStatus()));


    }

    @Test
    public void createPet3() {

        Map<String, Object> petPaload = new HashMap<>();
        petPaload.put(NAME, "Hatiko");
        petPaload.put(STATUS, "gone");
        petPaload.put("age", 7);
        petPaload.put("id", 1088);
        String[] urls = {"s3.amazon.com/myPet/bestpic/jpg"};
        petPaload.put("photoUrls", urls);

        given().spec(requestSpecification).body(petPaload)
                .when().post()
                .then().spec(responseSpecification)
                .and().body(NAME, Matchers.is(petPaload.get(NAME))).log().body();
    }

}
