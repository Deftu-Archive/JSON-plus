package ga.matthewtgm.json.parsing;

import com.google.gson.Gson;
import ga.matthewtgm.json.base.Json;

public class JsonParser {

    public static Json parse(String s) {
        final Gson gson = new Gson();
        return gson.fromJson(s, Json.class);
    }

}