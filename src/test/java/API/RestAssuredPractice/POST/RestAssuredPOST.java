package API.RestAssuredPractice.POST;

import API.pojo.PetPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RestAssuredPOST {

    @Before
    public void setup(){
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";
    }
    @Test
    public void createPet(){
        File petPaylaodFile = new File("target/pet2.json");
        given().accept(ContentType.JSON).contentType(ContentType.JSON).body(petPaylaodFile)
                .when().post()
                .then().statusCode(200).and().contentType(ContentType.JSON);

    }


    @Test
    public void createPet2(){
        PetPojo petPojo = new PetPojo();
        List<String> photoUrl = new ArrayList<>();
        photoUrl.add("http://google.com");
        petPojo.setId(111);
        petPojo.setName("Noorik");
        petPojo.setStatus("alive");
        petPojo.setPhotoUrls(photoUrl);

        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(petPojo)
                .when().post()
                .then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
                .and().body("name", Matchers.is("Noorik"));


    }
}
