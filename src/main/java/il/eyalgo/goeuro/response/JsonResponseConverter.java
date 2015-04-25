package il.eyalgo.goeuro.response;

import il.eyalgo.goeuro.model.Endpoint;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class JsonResponseConverter {
    private final Gson gson;

    public JsonResponseConverter() {
        gson = new Gson();
    }

    public List<Endpoint> jsonArrayToEndpoints(String jsonArray) {
        return Arrays.asList(gson.fromJson(jsonArray, Endpoint[].class));
    }
}
