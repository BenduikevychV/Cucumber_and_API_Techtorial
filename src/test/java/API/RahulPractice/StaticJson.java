package API.RahulPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StaticJson {

    static String id;
    @Test
    public void addBook() throws IOException {
        RestAssured.baseURI = "http://216.10.245.166";
        Response response = given().header("Content-Type", ContentType.JSON)
                .body(Payload.generateStringFromResource("target/RahulPractice.json"))
                .when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200).extract().response();

        JsonPath jsonPath = response.jsonPath();
        String actualResult = jsonPath.getString("ID");
        String expectedResult = "ttt75998";
        id = actualResult;
        System.out.println(actualResult);
        Assert.assertEquals(actualResult,expectedResult);
    }
    @Test
    public void getBook(){
        RestAssured.baseURI = "http://216.10.245.166";
        System.out.println(id);
        Response response = given().queryParam("ID",id).header("accept",ContentType.JSON)
                .when().get("/Library/GetBook.php")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON).extract().response();

        JsonPath jsonPath = response.jsonPath();
        String expectedAisle = id.substring(3);
        List aisle = jsonPath.getList("aisle");
        System.out.println(aisle);
        Assert.assertEquals(expectedAisle,aisle.toString());
    }
}
