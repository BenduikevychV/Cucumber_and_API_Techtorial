package API;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import java.net.URISyntaxException;
import static Utils.PayloadUtil.getPetPayload;

public class POSTRequestIntro {

    @Test
    public void postRequest() throws URISyntaxException, IOException {

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

        HttpClient client = HttpClientBuilder.create().build();
        //https://reqres.in/api/users
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("reqres.in");
        uriBuilder.setPath("api/users");

        // Specify the HTTP method
        HttpPost httpPost = new HttpPost(uriBuilder.build());
        // add header parameter
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");

        HttpEntity entityBuilder = new StringEntity("{\n" +
                "    \"name\": \"Volodymyr\",\n" +
                "    \"job\": \"SDET\"\n" +
                "}");

        httpPost.setEntity(entityBuilder);

        HttpResponse response = client.execute(httpPost);

        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusLine().getStatusCode());

    }


        @Test
        public void postRequest2() throws URISyntaxException, IOException {
        //https://petstore.swagger.io/v2/pet
        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet");

        HttpPost httpPost = new HttpPost(uriBuilder.build());

        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");

        HttpEntity entity = new StringEntity(getPetPayload(7771, "Ronald", "do not touch"));

        httpPost.setEntity(entity);

        HttpResponse response1 = client.execute(httpPost);

        Assert.assertEquals(HttpStatus.SC_OK, response1.getStatusLine().getStatusCode());

    }

    @Test
    public void putRequest() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        //https://reqres.in/api/users
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("reqres.in");
        uriBuilder.setPath("api/users");

        // Specify the HTTP method
        HttpPut put = new HttpPut(uriBuilder.build());
        // add header parameter
        put.setHeader("Content-Type","application/json");
        put.setHeader("Accept","application/json");

        HttpEntity entityBuilder = new StringEntity("{\n" +
                "    \"name\": \"Vova\",\n" +
                "    \"job\": \"SDET\"\n" +
                "}");

        put.setEntity(entityBuilder);

        HttpResponse response2 = client.execute(put);

        Assert.assertEquals(HttpStatus.SC_OK,response2.getStatusLine().getStatusCode());

    }

}
