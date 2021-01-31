package API.RahulPractice.DeserializationSerialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SeralizeTest {

    public static void main(String[] args) throws IOException {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        RestAssured.basePath = "maps/api/place/add/json";

        AddPlace place = new AddPlace();

        place.setAccuracy(50);
        place.setAddress("29, side layout, cohen 09");
        place.setLanguage("French-IN");
        place.setName("Frontline house");
        place.setPhone_number("(+91) 983 893 3937");
        place.setWebsite("http://google.com");

        List<String> types = new ArrayList<>();
        types.add("shoe park");
        types.add("shop");
        place.setTypes(types);
        Location location = new Location();
        location.setLng(33.427362);
        location.setLat(-38.383494);
        place.setLocation(location);


        Response response = given().queryParam("key","qaclick123").body(place)
                .when().post()
                .then().assertThat().statusCode(200).extract().response();

        String resp = response.asString();
        System.out.println(resp);

        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.getString("place_id"));


        JsonPath js = new JsonPath(resp);
        System.out.println(js.getString("place_id"));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("target/place.json"),place);
    }
}
