package API.reatassured;

import API.pojo.football.CompetitionsPojo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredAdvanced {


    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;
    @Before
    public void setup(){
        RestAssured.baseURI = "https://api.football-data.org";
        RestAssured.basePath ="v2/competitions";
        requestSpecification = new RequestSpecBuilder().setAccept(ContentType.JSON).build();
        responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        response= given().spec(requestSpecification).when().get().then().spec(responseSpecification).extract().response();

    }


    @Test
    public void adv1(){

        CompetitionsPojo footballPojo = response.getBody().as(CompetitionsPojo.class);

        System.out.println(footballPojo.getCompetitions().get(1).get("name"));

        List<Map<String,Object>> competitionsList = footballPojo.getCompetitions();

        for (Map<String,Object> map : competitionsList){
            int id = (int)map.get("id");

            if(id>=2100){
                System.out.println(map.get("name"));
            }
        }

        System.out.println(competitionsList.size());

    }

    @Test
    public void adv2(){

        JsonPath jsonPath = response.jsonPath();
        List<Map<String,Object>> competitions = jsonPath.getList("competitions");

        List<Integer> id1 = new ArrayList<>();

        int count = jsonPath.getInt("count");
        Assert.assertEquals(149,count);
        System.out.println(count);

        for (Map<String,Object> map : competitions){
            int id = (int)map.get("id");

            if(id>=2100){
                System.out.println(map.get("name"));
                id1.add(id);
            }
        }

        System.out.println(id1.size());
    }

    @Test
    public void adv3(){


        List<String> competitionList = response.path("competitions.findAll { it.id >= 2100 }.name");
        System.out.println(competitionList);
        System.out.println(competitionList.size());

        int count = response.path("count");
        Assert.assertEquals(149,count);
        System.out.println(count);
    }

    @Test
    public void adv4(){

        List<String> listOfStrings = response.path("competitions.findAll { it.area.name == 'Mexico' }.name");
        System.out.println(listOfStrings);

    }
}
