package API;

import Utils.PayloadUtil;
import com.github.javafaker.Faker;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

public class HomeWorkPOST {


         /*
    1. Open/ launch client (open POSTMAN, terminal)
    2. Specify the method type (GET/POST/PUT/DELETE)
    3. Specify the URL/URI( Uniform Resource locator)/(Uniform Resource Identifier) URL == URI
    4. Add query parameter (if needed)
    5. Add header parameters (content-type, accept)
    6. Add body parameter (for POST)
    7. Execute (click on send button)
    8. Check the response status code
    9. Check the response body
     */

    @Test
    public void createClient() throws URISyntaxException, IOException {

        for (int i = 0; i < 25; i++) {

            HttpClient client = HttpClientBuilder.create().build();

            URIBuilder uriBuilder = new URIBuilder();
            // https://petstore.swagger.io/v2/pet
            uriBuilder.setScheme("https");
            uriBuilder.setHost("petstore.swagger.io");
            uriBuilder.setPath("v2/pet");

            HttpPost httpPost = new HttpPost(uriBuilder.build());

            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Accept", "application/json");

            Faker faker = new Faker();
            String gogName = faker.dog().name();
            String status = faker.funnyName().name();
            HttpEntity httpEntity = new StringEntity(PayloadUtil.getPetPayload(i, gogName, status));

            httpPost.setEntity(httpEntity);

            HttpResponse response = client.execute(httpPost);

            Assert.assertEquals(HttpStatus.SC_OK,response.getStatusLine().getStatusCode());

            System.out.println( i+"->" +gogName+"->" + status);
        }


    }


    @Test
    public void updateClient(){

    }



}
