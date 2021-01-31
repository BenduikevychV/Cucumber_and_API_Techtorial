package API.RahulPractice.Jira;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public  class JiraSessionFrlterPractice {

    @Test
    public void test1(){
        RestAssured.baseURI = "http://localhost:8080";

        SessionFilter session = new SessionFilter();

        Response responseLogin = given().relaxedHTTPSValidation().header("content-Type", ContentType.JSON).body("{\n" +
                "    \"username\": \"Volodymyr\",\n" +
                "    \"password\": \"StepanSraka\"\n" +
                "}")
                .filter(session).log().all()
                .when().post("rest/auth/1/session")
                .then().assertThat().statusCode(200).extract().response();

        String expectedMessage = "Hi! how are your doing";

        Response responseCreateIssue = given().header("content-Type",ContentType.JSON).body("{\n" +
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
                .filter(session)
                .when().post("rest/api/2/issue")
                .then().assertThat().extract().response();

        JsonPath jsonPath = responseCreateIssue.jsonPath();
        String issueID = jsonPath.getString("id");

        Response responseAddComment = given().pathParam("key",issueID).header("content-Type",ContentType.JSON).body("{\n" +
                "    \"body\": \""+expectedMessage+" \",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}")
                .filter(session).log().all()
                .when().post("rest/api/2/issue/{key}/comment")
                .then().assertThat().statusCode(201).extract().response();

        JsonPath jsonPath1 = responseAddComment.jsonPath();
        String commentID = jsonPath1.getString("id");

        // ADd Attachment
//        given().header("X-Atlassian-Token","no-check").filter(session).pathParam("key",id)
//                .header("content-Type","multipart/form-data")
//                .multiPart("file",new File("target/jira.txt"))
//                .when().post("rest/api/2/issue/{key}/attachments")
//                .then().log().all().assertThat().statusCode(200).extract().response();

        System.out.println("==========");
        String issueDetails = given().filter(session).pathParam("key",issueID).header("content-Type",ContentType.JSON)
                .queryParam("fields","comment")
                .when().get("rest/api/2/issue/{key}")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        System.out.println("====>>>>"+issueDetails);

      JsonPath js = new JsonPath(issueDetails);

      int commentsCount = js.getInt("fields.comment.comments.size");
        String actualMessage ="";
      for (int i = 0;i<commentsCount; i++){
          String commentIdIssue = js.get("fields.comment.comments["+i+"].id").toString();
          if (commentIdIssue.equalsIgnoreCase(commentID)){
               actualMessage = js.getString("fields.comment.comments["+i+"].body").trim();
          }
      }

      Assert.assertEquals(actualMessage,expectedMessage);

    }
}
