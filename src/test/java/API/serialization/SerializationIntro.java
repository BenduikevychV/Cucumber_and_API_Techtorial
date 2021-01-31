package API.serialization;

import Utils.PayloadUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class SerializationIntro {



    @Test
    public void serializ1() throws IOException {

       Pet pet = new Pet("Chuck Norris",100,"alive");
        pet.setId(707);

        pet.setPhotoUrl("https://s3.petpics.amazon.com");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("target/pet.json"),pet);

    }

    @Test
    public void serializ2() throws IOException {
        Car car = new Car("BMW",2018,15000);

        car.setPrice(1000);
        car.setModel("audi");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("target/car.json"),car);

    }


        @Test
        public void createPet() throws URISyntaxException, IOException {
            //https://petstore.swagger.io/v2/pet
            HttpClient client = HttpClientBuilder.create().build();

            URIBuilder uriBuilder = new URIBuilder();
            uriBuilder.setScheme("https").setHost("petstore.swagger.io").setPath("v2/pet");

            HttpPost post = new HttpPost(uriBuilder.build());
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Accept", "application/json");

            String petPayload = PayloadUtil.generateStringFromResource("target/pet2.json");

            HttpEntity entity = new StringEntity(petPayload);
            post.setEntity(entity);

            HttpResponse response= client.execute(post);

            Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

    }





}
