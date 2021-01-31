package API;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIStarWars {

    @Test
    public void test1() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
//        https://swapi.dev/api/planets
        uriBuilder.setScheme("https");
        uriBuilder.setHost("swapi.dev");
        uriBuilder.setPath("api/planets");

        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(get);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> parseResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        List<Map<String, Object>> results = (List<Map<String, Object>>) parseResponse.get("results");


        Map<String, Long> planetsPopulationMap = new HashMap<>();

        for (int i = 0; i < results.size(); i++) {

            String planet = results.get(i).get("name").toString();
            Long population = 0l;
            try {
                population = Long.parseLong(results.get(i).get("population").toString());
            } catch (NumberFormatException e) {
                System.out.println("Population value numric " + e);
            }
            planetsPopulationMap.put(planet, population);
        }
        System.out.println(planetsPopulationMap);

        // or
        Map<String, String> planetsPopulationMap1 = new HashMap<>();

        for (int i = 0; i < results.size(); i++) {

            String planet = results.get(i).get("name").toString();
            String population = (String) results.get(i).get("population");
            planetsPopulationMap1.put(planet, population);
        }
        System.out.println(planetsPopulationMap1);

    }


}
