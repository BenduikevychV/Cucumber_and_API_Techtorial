package API.RestAssuredPractice.GET;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class intro2 {

    @Test
    public void test1() {
//        https://breakingbadapi.com/api/characters/50
        given().header("accept", ContentType.JSON).when().get("https://breakingbadapi.com/api/characters/50")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .body("[0].name", Matchers.equalToIgnoringCase("juan bolsa"))
                .and().body("[0].occupation", Matchers.hasSize(1)).log().body();

    }

    @Test
    public void test2() {
        given().header("accept", ContentType.JSON).when().get("https://breakingbadapi.com/api/characters")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .rootPath("[49]")
                .body("status", Matchers.equalToIgnoringCase("Deceased"))
                .body("nickname", Matchers.is("Don Juan"))
                .body("appearance.size()", Matchers.greaterThan(0)).log().body()
                .body("appearance[1]", Matchers.is(4));
    }

    @Test
    public void test3(){
        RestAssured.baseURI = "https://api.got.show";
        RestAssured.basePath = "api/map/characters";

        given().header("accept",ContentType.JSON).when().get("byId/5cc0743504e71a0010b852d9")
                .then().log().ifStatusCodeIsEqualTo(404).assertThat().statusCode(200).contentType(ContentType.JSON)
                .rootPath("data")
                .body("books",Matchers.hasItem("A Clash of Kings"))
                .body("dateOfBirth",Matchers.equalTo(283))
                .body("house",Matchers.is("House Stark"))
                .body("male",Matchers.isA(Boolean.class));
    }
}
