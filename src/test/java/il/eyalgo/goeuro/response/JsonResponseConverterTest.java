package il.eyalgo.goeuro.response;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import il.eyalgo.goeuro.model.Endpoint;
import il.eyalgo.goeuro.model.GeoPosition;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class JsonResponseConverterTest {
    private JsonResponseConverter converter;

    @Before
    public void setup() {
        converter = new JsonResponseConverter();
    }

    @Test
    public void when_json_empty_array_then_convert_to_no_endpoints() {
        String jsonArray = "[]";
        List<Endpoint> endpoints = converter.jsonArrayToEndpoints(jsonArray);
        assertThat("empty json input should convert to empty list", endpoints, emptyCollectionOf(Endpoint.class));
    }
    
    @Test
    public void should_convert_json_array_to_endpoints() {
        String jsonArray = "[{\"_id\":377078,\"coreCountry\":true,\"country\":\"Germany\",\"countryCode\":\"DE\",\"distance\":null,\"fullName\":\"Potsdam, Germany\",\"geo_position\":{\"latitude\":52.39886,\"longitude\":13.06566},\"iata_airport_code\":null,\"inEurope\":true,\"key\":null,\"locationId\":9254,\"name\":\"Potsdam\",\"type\":\"location\"},{\"_id\":410978,\"coreCountry\":false,\"country\":\"USA\",\"countryCode\":\"US\",\"distance\":null,\"fullName\":\"Potsdam, USA\",\"geo_position\":{\"latitude\":44.66978,\"longitude\":-74.98131},\"iata_airport_code\":null,\"inEurope\":false,\"key\":null,\"locationId\":43214,\"name\":\"Potsdam\",\"type\":\"location\"}]";
        List<Endpoint> endpoints = converter.jsonArrayToEndpoints(jsonArray);
        assertThat("expecting two endpoints", endpoints.size(), equalTo(2));
        Endpoint expectedEndpoint1 = new Endpoint(null, "377078", "Potsdam", "location", new GeoPosition(52.39886, 13.06566));
        Endpoint expectedEndpoint2 = new Endpoint(null, "410978", "Potsdam", "location", new GeoPosition(44.66978, -74.98131));
        assertThat("endpoints not converted correctly", endpoints, containsInAnyOrder(expectedEndpoint1, expectedEndpoint2));
    }
}
