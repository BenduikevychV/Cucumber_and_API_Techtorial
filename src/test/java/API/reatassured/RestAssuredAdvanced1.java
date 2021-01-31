package API.reatassured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RestAssuredAdvanced1 {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;

    @Before
    public void setup() {
        RestAssured.baseURI = "https://api.football-data.org";
        RestAssured.basePath = "v2/competitions/2000/scorers";
        requestSpecification = new RequestSpecBuilder().setAccept(ContentType.JSON)
                .addHeader("X-Auth-Token", "72bd7f61c55842bd88ee905ed35f15db").build();
        responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        response = given().spec(requestSpecification).when().get().then().spec(responseSpecification).extract().response();

    }

    @Test
    public void adv1() {

        int numberOfGoalsByHarryKane = response.path("scorers.find { it.player.name == 'Harry Kane' }.numberOfGoals");
        Assert.assertEquals(6, numberOfGoalsByHarryKane);
    }

    @Test
    public void adv2() {
        String countryOfBirthOfDenis = response.path("scorers.find {it.player.name == 'Denis Cheryshev' }.player.countryOfBirth");
        String teamName = response.path("scorers.find {it.player.name == 'Denis Cheryshev' }.team.name");
        Assert.assertEquals("Russia", countryOfBirthOfDenis);
        Assert.assertEquals("Russia", teamName);
    }


    @Test
    public void adv3() {
        String playerWithMaxGaols = response.path("scorers.max {it.numberOfGoals }.player.name");
        System.out.println(playerWithMaxGaols);
        String playerWithMinGaols = response.path("scorers.min {it.numberOfGoals }.player.name");
        System.out.println(playerWithMinGaols);
    }

    @Test
    public void adv4() {

        int minGoals = response.path("scorers.min {it.numberOfGoals }.numberOfGoals");

        List<String> minScores = response.path("scorers.findAll {it.numberOfGoals == "+ minGoals +"}.player.name");
        System.out.println(minScores);
    }

    @Test
    public void adv5() {

        response.then().body("scorers.find {it.player.name == 'Harry Kane'}.numberOfGoals", Matchers.equalTo(6));

    }

    @Test
    public void adv6() {

        response.then().assertThat().body("scorers.findAll {it.team.name == 'Russia' }.size", Matchers.greaterThan(0));

    }

    @Test
    public void adv7(){
        response.then().body("scorers.collect {it.team.name }",Matchers.hasItem("France"));
        response.then().body("scorers.collect {it.team.name }",Matchers.hasItems("Portugal","England"));
    }

}
