package API;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.net.URISyntaxException;
public class GETRequestIntro {
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
    public void getRequest() throws  IOException {
        //Open a client
        HttpClient client = HttpClientBuilder.create().build();
        // Specify URL/URI / endpoint
        // http://petstore.swagger.io/v2/pet{petId}
        URIBuilder uri = new URIBuilder();
        uri.setScheme("http");
        uri.setHost("petstore.swagger.io");
        uri.setPath("v2/pet/9977");
        // Specify the HTTP method (GET)
        try{

        HttpGet get = new HttpGet(uri.build());
        //Add header parameters
        get.setHeader("Accept", "application/json");
        //Execute
        HttpResponse response = client.execute(get);

        // request ->
        Assert.assertEquals(200, response.getStatusLine().getStatusCode());
        System.out.println("Status code for request is: "+response.getStatusLine().getStatusCode());

        Assert.assertEquals("application/json",response.getEntity().getContentType().getValue());


        System.out.println(response.getEntity().getContentType().getValue());


        }catch (URISyntaxException e){
            e.fillInStackTrace();
        }

    }

    @Test
    public void getRequest2(){
        // https://petstore.swagger.io/v2/pet/findByStatus?status=sold
        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet/findByStatus");
        uriBuilder.setCustomQuery("be quiet");

        try {

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept","application/json");

        HttpResponse response = client.execute(httpGet);

        Assert.assertEquals(200,response.getStatusLine().getStatusCode());

        Assert.assertEquals("application/json",response.getEntity().getContentType().getValue());

            System.out.println(response.getEntity().getContentType().getValue());
            System.out.println(response.getStatusLine().getStatusCode());

        }catch (IOException exception){
            exception.getStackTrace();
        }catch (URISyntaxException exception){
            exception.getStackTrace();
        }





    }
}
