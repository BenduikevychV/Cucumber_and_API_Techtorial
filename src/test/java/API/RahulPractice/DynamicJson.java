package API.RahulPractice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class DynamicJson {

    static String id;

    @Test
    public void addBook() {

        RestAssured.baseURI = "http://216.10.245.166";
        Response response = given().header("Content-Type", "application/json").header("accept", "application/json")
                .body(Payload.AddBook("chuck", "1299"))
                .when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response();

        JsonPath js = response.jsonPath();
        String id = js.get("ID");
        System.out.println(id);
    }

    @Test
    public void addBook1() {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().header("Content-Type", "application/json")
                .body(Payload.AddBook("chuck", "8393"))
                .when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = ReUsableMethods.rawToJson(response);

        id = js.getString("ID");
        System.out.println(id);
    }

    @Test
    public void deleteBook() {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().header("Content-Type", "application/json")
                .body(Payload.DeleteBook(id))
                .when().delete("/Library/DeleteBook.php")
                .then().assertThat().statusCode(200)
                .and().extract().response().asString();

        JsonPath js = ReUsableMethods.rawToJson(response);

        String expectedMessage = "book is successfully deleted";
        String actualMessage = js.getString("msg");
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @DataProvider(name="BooksData")
    public Object[][] getData(){
       return new Object[][] {
               {"ralf","4755"},
               {"donald","4755"},
               {"mac","4745"}
       };
    }

    @Test(dataProvider = "BooksData")
    public void addBook2(String isbn, String aisle ){
        RestAssured.baseURI = "http://216.10.245.166";
        Response response = given().header("Content-Type","application/json").body(Payload.AddBook(isbn, aisle))
                .when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200).extract().response();

        JsonPath jsonPath = response.jsonPath();
        String actualID = jsonPath.getString("ID");
        String expectedID = isbn+aisle;
        System.out.println(actualID);
        Assert.assertEquals(actualID,expectedID);

    }


}
