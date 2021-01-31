package API.JiraAPI;

import API.JiraAPI.JiraPojo.AllPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CatFact {

    @Before
    public void setup() throws URISyntaxException, IOException {

        RestAssured.baseURI = "http://cat-fact.herokuapp.com";
        RestAssured.basePath = "facts";
    }


    @Test
    public void getCatFact() {
        Response response = given().header("accept", ContentType.JSON).when().get()
                .then().statusCode(200).and().contentType(ContentType.JSON).extract().response();
        
        AllPojo catParsed = response.as(AllPojo.class);
        Map<String, String> nameObject = (Map<String, String>) catParsed.getAll().get(24).getUser().get("name");

        System.out.println(catParsed.getAll().get(24).get_id());
        System.out.println(catParsed.getAll().get(24).getText());

        System.out.println(nameObject.get("first"));
        System.out.println(nameObject.get("last"));
    }

}
