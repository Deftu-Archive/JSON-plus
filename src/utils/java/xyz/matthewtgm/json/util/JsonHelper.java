package xyz.matthewtgm.json.util;

import xyz.matthewtgm.json.entities.JsonElement;
import xyz.matthewtgm.json.parser.JsonParser;

public class JsonHelper {

    /*public static String makePretty(Json object) {
        return GlobalGson.getPrettyGson().toJson(object);
    }

    public static String makePretty(String jsonStr, Class<? extends Json> jsonType) {
        return GlobalGson.getPrettyGson().toJson(JsonParser.parse(jsonStr, jsonType));
    }*/

    public static boolean isValidJson(Object o) {
        String str = o.toString();
        boolean valid = false;

        try {
            JsonElement element = JsonParser.parse(str);
            if (element != null) valid = true;
        } catch (Exception ignored) {}

        return valid;
    }

}