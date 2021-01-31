package API.reatassured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RestAssuredPractice1 {

    @Test
    public void test1(){
        Response response = given().header("accept", ContentType.JSON)
                .when().get("https://api.football-data.org/v2/competitions")
                .then().log().ifValidationFails().assertThat().statusCode(200).contentType(ContentType.JSON)
                .and().extract().response();

        int numberOfAvailableSeasons = response.path("competitions.find {it.area.id == 2001} .numberOfAvailableSeasons");
        Assert.assertEquals(numberOfAvailableSeasons,2);
    }

    @Test
    public void test2(){
        Response response = given().header("accept",ContentType.JSON)
                .when().get("https://api.football-data.org/v2/competitions")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .and().extract().response();

        List<Integer> areaName = response.path("competitions.findAll {it.area.name == 'Ukraine'}.currentSeason.id");
        for (Integer id : areaName){
            System.out.println(id);
        }
        System.out.println(areaName.size());
    }
    @Test
    public void test3(){
        given().header("accept",ContentType.JSON)
                .when().get("https://api.football-data.org/v2/competitions")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .and().body("competitions[1].currentSeason.startDate", Matchers.equalTo("2019-07-27"));

        Response response = given().header("accept",ContentType.JSON)
                .when().get("https://api.football-data.org/v2/competitions")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON).extract().response();
        String str = response.path("competitions[1].currentSeason.startDate");
        System.out.println(str);
    }
}
