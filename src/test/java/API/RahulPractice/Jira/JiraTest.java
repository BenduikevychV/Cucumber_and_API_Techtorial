package API.RahulPractice.Jira;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class JiraTest {

    public static void main(String[] args) {

        RestAssured.baseURI = "http://localhost:8080";

        Response responseLogin = given().header("content-Type", ContentType.JSON).body("{\n" +
                "    \"username\": \"Volodymyr\",\n" +
                "    \"password\": \"StepanSraka\"\n" +
                "}")
                .when().post("rest/auth/1/session")
                .then().assertThat().statusCode(200).extract().response();

        JsonPath jsResponseLogin = responseLogin.jsonPath();
        String cookieName = jsResponseLogin.getString("session.name");
        String cookieValue = jsResponseLogin.getString("session.value");
        String cookie = cookieName+"="+cookieValue;

        Response responseCreate =given().header("content-Type",ContentType.JSON).header("cookie",cookie).body("{\n" +
                "    \"fields\": {\n" +
                "       \"project\":\n" +
                "       {\n" +
                "          \"key\": \"RES\"\n" +
                "       },\n" +
                "       \"summary\": \"Debit card defect\",\n" +
                "       \"description\": \"Creating my second bug\",\n" +
                "       \"issuetype\": {\n" +
                "          \"name\": \"Bug\"\n" +
                "       }\n" +
                "   }\n" +
                "}")
                .when().post("rest/api/2/issue")
                .then().assertThat().statusCode(201).extract().response();

        JsonPath jsCreateIssue = responseCreate.jsonPath();

        String idPath = jsCreateIssue.getString("id");


        Response responseComment = given().header("content-Type",ContentType.JSON).header("cookie",cookie).body("{\n" +
                "    \"body\": \"Hey I have commented from Rest API\",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}")
                .when().post("rest/api/2/issue/"+idPath+"/comment")
                .then().assertThat().statusCode(201).extract().response();

    }
}
