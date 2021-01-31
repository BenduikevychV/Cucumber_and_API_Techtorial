package API.reatassured;

import API.pojo.BBCPojo;
import io.restassured.RestAssured;

import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RestAssuredDesirilization {


    @Before
    public void setup(){
        RestAssured.baseURI="https://breakingbadapi.com";
        RestAssured.basePath="api/characters";
    }

    @Test
    public void deser(){

        List<BBCPojo> characterResp = given().header("accept", ContentType.JSON).when().get("/50")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .extract().as( new TypeRef<List<BBCPojo>>() {});

        Assert.assertEquals("Juan Bolsa",characterResp.get(0).getName());

    }

    @Test
    public void deser2(){

        List<BBCPojo> characterResp = given().header("accept", ContentType.JSON).when().get("/35")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .extract().as( new TypeRef<List<BBCPojo>>() {});

        Assert.assertEquals("Unknown",characterResp.get(0).getBirthday());
        Assert.assertEquals(2,characterResp.get(0).getAppearance().size());
        Assert.assertEquals("Dr. Delcavoli",characterResp.get(0).getNickname());

    }

}
