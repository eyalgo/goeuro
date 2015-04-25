package il.eyalgo.goeuro.request;

import il.eyalgo.goeuro.model.Endpoint;
import il.eyalgo.goeuro.response.JsonResponseConverter;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.utils.URIBuilder;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

public class GoEuroRequest {

    public GoEuroRequest() {
    }

    public String callApi(String location) throws Exception {
        URI uri = createUri(location);

        Resty resty = new Resty();
        JSONResource jsonResource = resty.json(uri);
        return jsonResource.array().toString();
    }

    private URI createUri(String location) throws URISyntaxException {
        return new URIBuilder()
                .setScheme("http")
                .setHost("www.goeuro.com")
                .setPath("/GoEuroAPI/rest/api/v2/position/suggest/en/" + location)
                .build();
    }

    public static void main(String[] args) throws Exception {
        GoEuroRequest goEuroRequest = new GoEuroRequest();
        String tlvArray = goEuroRequest.callApi("tel aviv");
        System.out.println(tlvArray);

        JsonResponseConverter converter = new JsonResponseConverter();
        List<Endpoint> endpoints = converter.jsonArrayToEndpoints(tlvArray);
        for (Endpoint endpoint : endpoints) {
            System.out.println(endpoint);
        }
    }
}
