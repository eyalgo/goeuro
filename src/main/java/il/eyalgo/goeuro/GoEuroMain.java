package il.eyalgo.goeuro;

import il.eyalgo.goeuro.model.Endpoint;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URIUtils;

import com.google.gson.Gson;

import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;
import us.monoid.web.JSONResource;
import us.monoid.web.Resty;
import us.monoid.web.XMLResource;


public class GoEuroMain {
    private final static String DEFAULT_CSV_FILE = "goeuro.csv";

    public GoEuroMain() {
    }

    public static void main(String[] args) throws URISyntaxException {
        try {
            String location = "potsdam";
            
            
            URI uri = new URIBuilder()
            .setScheme("http")
            .setHost("www.goeuro.com")
            .setPath("/GoEuroAPI/rest/api/v2/position/suggest/en/" + location).build();
            
//            String query = "http://www.goeuro.com/GoEuroAPI/rest/api/v2/position/suggest/en/"+URI.create(location).toString();
            Gson gson = new Gson();
            Resty resty = new Resty();
            JSONResource json = resty.json(uri);
            JSONArray jsonArray = json.array();
            System.out.println(jsonArray.toString());
            
            Endpoint[] fromJson = gson.fromJson(jsonArray.toString(), Endpoint[].class);
            for (Endpoint endpoint : fromJson) {
                System.out.println(endpoint);
            }
        } catch (IOException | JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        new Resty().json(anUri);
        if (args.length < 1) {
            help();
            System.exit(1);
        }
    }

    private static void help() {
        System.out.println("Please provide correct input");
        System.out.println();
        System.out.println("Example: java ­jar GoEuroTest.jar \"STRING\"");
        System.out.println("You can set the ouput file as well.");
        System.out.println("Example: java ­jar GoEuroTest.jar \"STRING\" \"File_name\"");
        System.out.println(String.format("If output file not provided, then %s will be created", DEFAULT_CSV_FILE));
    }
}
