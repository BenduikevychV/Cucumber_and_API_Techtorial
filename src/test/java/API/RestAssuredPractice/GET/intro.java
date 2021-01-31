package API.RestAssuredPractice.GET;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class intro {

    @Test
    public void test1() {

// http://swapi.dev/api/planets

        given().header("accept", ContentType.JSON)
                .when().get("http://swapi.dev/api/planets")
                .then().statusCode(200)
                .and().contentType(ContentType.JSON);

    }

    @Test
    public void test2() {
        given().header("contentType", ContentType.JSON).header("accept", ContentType.JSON)
                .get("http://swapi.dev/api/planets/1")
                .then().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().assertThat().body("name", equalTo("Tatooine"));
    }

    @Test
    public void test3() {
        given().header("accept", ContentType.JSON)
                .get("http://swapi.dev/api/planets")
                .then().log().ifStatusCodeIsEqualTo(404).statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().assertThat().body("results[0].population", equalTo("200000"))
                .and().body("results[1].diameter", Matchers.is("12500"));
    }
}
