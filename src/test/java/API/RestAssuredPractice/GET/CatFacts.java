package API.RestAssuredPractice.GET;

import API.JiraAPI.JiraPojo.AllPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CatFacts {

    @Before
    public void setup(){
        RestAssured.baseURI = "http://cat-fact.herokuapp.com";
        RestAssured.basePath = "facts";
    }

    @Test
    public void getFact(){
        given().header("ContentType", ContentType.JSON)
                .when().get().then().statusCode(200).contentType(ContentType.JSON);
    }
    @Test
    public void getFact2(){
        given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().get().then().statusCode(200).contentType(ContentType.JSON);
    }
    @Test
    public void getFact3(){

        Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().get().then().assertThat().statusCode(200).contentType(ContentType.JSON).extract().response();

        AllPojo catFacts = response.as(AllPojo.class);

        Assert.assertEquals("59a60b8e6acf530020f3586e",catFacts.getAll().get(1).get_id());
        String getAllIndexOne = catFacts.getAll().get(1).get_id();
        System.out.println(getAllIndexOne);

        System.out.println(catFacts.getAll().get(0).get_id());
        System.out.println(catFacts.getAll().get(0).getUser());

        Map<String,Object> user = (Map<String,Object>)catFacts.getAll().get(0).getUser().get("name") ;
        String id = (String) catFacts.getAll().get(0).getUser().get("_id") ;
        String first =(String) user.get("first");
        String last = (String) user.get("last");
//5a9ac18c7478810ea6c06381
        System.out.println(id);
        System.out.println(first +" "+last );
    }
}
