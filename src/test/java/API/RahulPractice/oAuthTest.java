package API.RahulPractice;

import API.RahulPractice.DeserializationSerialization.Api;
import API.RahulPractice.DeserializationSerialization.GetCourse;
import API.RahulPractice.DeserializationSerialization.WebAutomation;
import Utils.Driver;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class oAuthTest {

    public static void main(String[] args) throws InterruptedException {

        String [] courseTitle = {"Selenium Webdriver Java","Cypress","Protractor"};

//        WebDriver driver = Driver.getDriver();
//        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https:" +
//                "//accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj" +
//                ".apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
//
//        WebElement emailBox = driver.findElement(By.xpath("//input[@autocomplete='username']"));
//        emailBox.sendKeys("bendiukevych07@gmail.com");
//        emailBox.sendKeys(Keys.ENTER);
//        Thread.sleep(2000);
//        WebElement passwordBox = driver.findElement(By.xpath("//input[@type='password']"));
//        passwordBox.sendKeys("MorguletsGoba");
//        passwordBox.sendKeys(Keys.ENTER);
//        Thread.sleep(2000);
//        String url =  driver.getCurrentUrl();

//      google don't allow to automate the code above/beyond
        String url = "https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F3wEGoZmFg_8aNsYyXsDwh-r7IM6b-EhLPy34BHR3_M__YrcGkRxWPeN7lQP9d7NwJEDaEucJcYqjTeK2l7xGBqs&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=1&prompt=none#";
        String parsialcode = url.split("code=")[1];
        String code = parsialcode.split("&scope")[0];

//        String parisialcode1 = url.substring(url.indexOf("code=")+5);
//        String code1 = parisialcode1.substring(0,parisialcode1.indexOf("&scope"));
//        System.out.println(parisialcode1+" ====>>> my code");
//        System.out.println(parsialcode+" =====>>> Rahul code");
//        System.out.println(code1+" ====>>> my code");
//        System.out.println(code+" =====>>> Rahul code");

        System.out.println(code);

        String accessTokenResponse = given().urlEncodingEnabled(false)
                .queryParam("code",code)
                .queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParam("grant_type","authorization_code")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();

        JsonPath js = new JsonPath(accessTokenResponse);
        String accessToken = js.getString("access_token");

        GetCourse gc = given().queryParam("access_token",accessToken).expect().defaultParser(Parser.JSON)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php")
                .then().assertThat().statusCode(200).extract().response().as(GetCourse.class);

//        System.out.println(response);

        System.out.println(gc.getLinkedIn());
        System.out.println(gc.getInstructor());

        System.out.println(gc.getCourses().getWebAutomation().get(2).getCourseTitle());
        String ExpectedCourseTitle = "Protractor";
        Assert.assertEquals(gc.getCourses().getWebAutomation().get(2).getCourseTitle(),ExpectedCourseTitle);

        List<Api> apiCourses = gc.getCourses().getApi();

        for(int i=0; i<apiCourses.size();i++){
            if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")){
                System.out.println(apiCourses.get(i).getPrice()+" practice with Rahul");
            }
        }


        gc.getCourses().getMobile().get(0).getCourseTitle();

        List<String> expectedWebAutomation = Arrays.asList(courseTitle);

        List<String> actualWebAutomation = new ArrayList<>();

        List<WebAutomation> webAutomation = gc.getCourses().getWebAutomation();
        for (int i=0;i<webAutomation.size(); i++){

            actualWebAutomation.add(webAutomation.get(i).getCourseTitle());
        }

            Assert.assertEquals(expectedWebAutomation,actualWebAutomation);


    }
}
