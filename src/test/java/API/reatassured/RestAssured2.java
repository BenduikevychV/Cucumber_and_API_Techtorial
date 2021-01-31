package API.reatassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class RestAssured2 {


    @Test
    public void getReq1(){
       //  https://breakingbadapi.com/api/characters/50

        given().header("accept", ContentType.JSON).when().get("https://breakingbadapi.com/api/characters/50")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .and().body("[0].name", Matchers.is("Juan Bolsa"))
                .and().body("[0].occupation", Matchers.hasSize(1));

        Response response = given().header("accept",ContentType.JSON)
                .when().get("https://breakingbadapi.com/api/characters/50")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON).extract().response();
        String occupation = response.path("[0].occupation[0]");
        Assert.assertEquals("Mexican drug cartel boss",occupation);
    }

    @Test
    public void getReq2(){
        //  https://breakingbadapi.com/api/characters/50

        given().header("accept", ContentType.JSON).when().get("https://breakingbadapi.com/api/characters/50")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .and().body("[0].status", Matchers.equalTo("Deceased"))
                .and().body("[0].nickname", Matchers.equalTo("Don Juan"))
                .and().body("[0].appearance.size()", Matchers.greaterThan(1))
                .and().body("[0].appearance[1]", Matchers.is(4));


    }


    @Test
    public void getReq3(){
        //  https://breakingbadapi.com/api/characters/50

        given().header("accept", ContentType.JSON).when().get("https://breakingbadapi.com/api/characters/50")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .and().rootPath("[0]").body("status", Matchers.equalTo("Deceased"))
                .and().body("nickname", Matchers.equalTo("Don Juan"))
                .and().body("appearance.size()", Matchers.greaterThan(1))
                .and().body("appearance[1]", Matchers.is(4));


    }

    @Test
    public void getReq4(){
        //  https://api.got.show/api/map/characters/byId/5cc0743504e71a0010b852d9


        RestAssured.baseURI="https://api.got.show";
        RestAssured.basePath="api/map/characters/byId/5cc0743504e71a0010b852d9";


        given().header("accept", ContentType.JSON).when().get()
                .then().log().ifValidationFails().assertThat().statusCode(200).contentType(ContentType.JSON)
                .rootPath("data")
                .body("books", Matchers.hasItem("A Storm of Swords"))
                .body("dateOfBirth", Matchers.equalTo(283))
                .body("male", Matchers.isA(Boolean.class))
                .body("house", Matchers.is("House Stark"));

    }
}
