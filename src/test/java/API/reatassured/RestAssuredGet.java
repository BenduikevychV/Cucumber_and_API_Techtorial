package API.reatassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredGet {

    @Before
    public void setup(){
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";
    }

    @Test
    public void getPet(){



        given().accept(ContentType.JSON)
                .when().get("9999999").then().statusCode(200).log().body();

        given().accept(ContentType.JSON)
                .when().get("{id}",9999999).then().statusCode(200).log().body();

    }

    @Test
    public void getPet2(){

        given().accept(ContentType.JSON)
                .when().get("{id}",9999999).then().statusCode(200);

    }

    @Test
    public void getPet3(){

        given().accept(ContentType.JSON)
                .when().request("GET","9999999").then().statusCode(200);

    }

    @Test
    public void getPet4(){

        given().accept(ContentType.JSON)
                .when().request("GET","{id}","9999999").then().statusCode(200).log().body();

    }

}
