package API.homework;

import API.homework.PojoStarships.StarPojo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class Starships {


    @Test
    public void starshipTest() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        //https://swapi.dev/api/starships
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("swapi.dev").setPath("api/starships");

        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(get);

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        StarPojo parseObject = objectMapper.readValue(response.getEntity().getContent(), StarPojo.class);

        System.out.println(parseObject.getCount());
        System.out.println(parseObject.getNext());
        System.out.println(parseObject.getPrevious());


        for (int i=0; i< parseObject.getResults().size(); i++){
            System.out.println(parseObject.getResults().get(i).getMGLT());
            System.out.println(parseObject.getResults().get(i).getFilms());

        }
    }

}
