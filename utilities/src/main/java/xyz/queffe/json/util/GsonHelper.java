package xyz.queffe.json.util;

import xyz.queffe.json.entities.JsonElement;
import xyz.queffe.json.parser.JsonParser;

public class GsonHelper {

    public static JsonElement convert(com.google.gson.JsonElement element) {
        if (element == null)
            return null;
        return JsonParser.parse(element.toString());
    }

}