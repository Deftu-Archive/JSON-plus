package ga.matthewtgm.json.parsing;

import com.google.gson.Gson;
import ga.matthewtgm.json.objects.JsonObject;

public class JsonParser {

    public static JsonObject parse(String s) {
        final Gson gson = new Gson();
        return gson.fromJson(s, JsonObject.class);
    }

}