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

public class HomeWorkAPI {

    @Test
    public void getSong1() throws URISyntaxException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();

        // https://itunes.apple.com/search?term=linkinpark&limit=20
        uriBuilder.setScheme("https");
        uriBuilder.setHost("itunes.apple.com");
        uriBuilder.setPath("search");
        uriBuilder.setCustomQuery("term=linkinpark");
        uriBuilder.setCustomQuery("limit=20");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.setHeader("Accept","text/javascript; charset=utf-8");
        HttpResponse response = httpClient.execute(httpGet);

        Assert.assertEquals(200, response.getStatusLine().getStatusCode());
        System.out.println("Status code for request is: "+response.getStatusLine().getStatusCode());

        Assert.assertEquals("text/javascript; charset=utf-8",response.getEntity().getContentType().getValue());

    }
}
