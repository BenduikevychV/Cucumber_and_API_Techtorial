package API.reatassured;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredPut {

    RequestSpecification requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON).build();
    ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

    @Test
    public void updatePet() {
        File updatePetPayload = new File("target/pet2.json");

        given().spec(requestSpecification).body(updatePetPayload)
                .when().put("https://petstore.swagger.io/v2/pet")
                .then().spec(responseSpecification).contentType(ContentType.JSON)
                .and().body(RestAssuredPost.NAME, Matchers.equalTo("Chuck Norris"))
                .and().body(RestAssuredPost.STATUS, Matchers.equalTo("alive")).log().body();

    }

    @Test
    public void updatePet2(){
        Map<String,Object> updatePetPayload = new HashMap<>();
        updatePetPayload.put(RestAssuredPost.NAME,"Pretzel");
        updatePetPayload.put("id",9999999);
        updatePetPayload.put(RestAssuredPost.STATUS,"avalible");

        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(updatePetPayload)
                .when().put("https://petstore.swagger.io/v2/pet")
                .then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
                .and().body(RestAssuredPost.NAME, Matchers.equalTo(updatePetPayload.get(RestAssuredPost.NAME)))
                .and().body(RestAssuredPost.STATUS, Matchers.equalTo(updatePetPayload.get(RestAssuredPost.STATUS))).log().body();



    }

}
