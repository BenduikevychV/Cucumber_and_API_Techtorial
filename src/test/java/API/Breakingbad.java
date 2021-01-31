package API;

import API.pojo.BBCPojo;
import API.pojo.BreakingbadPojo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Breakingbad {

    private static final String ALIVE = "alive";
    private static final String DECEASED = "deceased";
    private static final String PRESUMED_DEAD = "presumed dead";
    private static final String UNKNOWN = "unknown status";


    @Test
    public void getCharacters() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
//        https://breakingbadapi.com/api/characters
        uriBuilder.setScheme("https");
        uriBuilder.setHost("breakingbadapi.com");
        uriBuilder.setPath("api/characters");

        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(get);

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<BreakingbadPojo> breakingbadPojo = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<List<BreakingbadPojo>>() {
                });

        System.out.println(breakingbadPojo.get(0).getName());

        Map<String, List<String>> charByCategory = new HashMap<>();
        List<String> alive = new ArrayList<>();
        List<String> deceased = new ArrayList<>();
        List<String> presumedDead = new ArrayList<>();
        List<String> unknownSatus = new ArrayList<>();

        for (int i = 0; i < breakingbadPojo.size(); i++) {

            if (breakingbadPojo.get(i).getStatus().equalsIgnoreCase(ALIVE)) {
                alive.add(breakingbadPojo.get(i).getName());
            } else if (breakingbadPojo.get(i).getStatus().equalsIgnoreCase(DECEASED)) {
                deceased.add(breakingbadPojo.get(i).getName());
            } else if (breakingbadPojo.get(i).getStatus().equalsIgnoreCase(PRESUMED_DEAD)) {
                presumedDead.add(breakingbadPojo.get(i).getName());
            } else {
                unknownSatus.add(breakingbadPojo.get(i).getStatus());
            }
        }


        charByCategory.put(ALIVE, alive);
        charByCategory.put(DECEASED, deceased);
        charByCategory.put(PRESUMED_DEAD, presumedDead);
        charByCategory.put(UNKNOWN, unknownSatus);


        System.out.println("List of alive characters: " + charByCategory.get(ALIVE));
        System.out.println("List of deceased characters: " + charByCategory.get(DECEASED));
        System.out.println("List of presumedDead characters: " + charByCategory.get(PRESUMED_DEAD));
        System.out.println("List of unknown characters: "+ charByCategory.get(UNKNOWN));
    }

    @Test
    public void getCharactersByID() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
//        https://breakingbadapi.com/api/characters
        uriBuilder.setScheme("https");
        uriBuilder.setHost("breakingbadapi.com");
        uriBuilder.setPath("api/characters/12");

        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(get);

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<BBCPojo> bbcPojos = objectMapper.readValue(response.getEntity().getContent(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, BBCPojo.class));

        System.out.println(String.format("%s is %s portrayed %s, in %s", bbcPojos.get(0).getName(), bbcPojos.get(0).getStatus()
        , bbcPojos.get(0).getPortrayed(),bbcPojos.get(0).getCategory()));

//        System.out.println(bbcPojos.get(0).getName()+" is "+ bbcPojos.get(0).getStatus()+" portrayed: "+
//                bbcPojos.get(0).getPortrayed()+", in "+ bbcPojos.get(0).getCategory());


    }

}
