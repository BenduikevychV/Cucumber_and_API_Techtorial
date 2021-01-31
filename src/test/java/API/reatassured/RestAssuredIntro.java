package API.reatassured;


import io.restassured.http.ContentType;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class RestAssuredIntro {

    @Test
    public void rest1() {
        // http://swapi.dev/api/planets/1

        given().header("contentType", ContentType.JSON).header("accept", ContentType.JSON)
                .when().get("http://swapi.dev/api/planets/1")
                .then().statusCode(200).and().contentType(ContentType.JSON);


    }
    @Test
    public void test1(){
        Response response = given().header("accept",ContentType.JSON)
                .when().get("http://swapi.dev/api/planets/1")
                .then().statusCode(200).and().contentType(ContentType.JSON)
                .extract().response();
        String diam = response.path("diameter");
        Assert.assertEquals("10465",diam);
    }
    @Test
    public void test7(){
        Response response = given().header("accept",ContentType.JSON)
                .when().get("http://swapi.dev/api/planets/25")
                .then().log().ifValidationFails().assertThat().statusCode(200).contentType(ContentType.JSON)
                .extract().response();
        String terrain = response.path("terrain");
        Assert.assertEquals("oceans, savannas, mountains, grasslands",terrain);
        System.out.println(terrain);
    }



    @Test
    public void rest2() {

        given().header("accept", ContentType.JSON)
                .when().get("http://swapi.dev/api/planets/1")
                .then().statusCode(200).and().contentType(ContentType.JSON)
                .and().assertThat().body("name", equalTo("Tatooine"))
        .and().assertThat().body("residents[3]",equalTo("http://swapi.dev/api/people/6/"));

    }

    @Test
    public void rest3() {
        given().header("accept", ContentType.JSON)
                .when().get("http://swapi.dev/api/planets")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .and().body("results[0].gravity", Matchers.is("1 standard"))
                .and().body("results[0].terrain", Matchers.isA(String.class));
    }
    @Test
    public void test2(){
        given().header("accept",ContentType.JSON)
                .when().get("http://swapi.dev/api/planets")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .and().body("results[0].films[4]",Matchers.equalTo("http://swapi.dev/api/films/6/"));
    }

    @Test
    public void rest4(){
        given().header("accept",ContentType.JSON)
                .when().get("http://swapi.dev/api/planets")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .and().body("results[1].residents[0]", Matchers.equalTo("http://swapi.dev/api/people/5/"));
    }

}
