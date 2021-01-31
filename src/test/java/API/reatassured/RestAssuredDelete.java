package API.reatassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredDelete {

    @Test
    public void deletePet(){

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";

        given().accept(ContentType.JSON)
                .when().delete("9999999")
                .then().statusCode(200).contentType(ContentType.JSON).log().body();


    }

}
