package xyz.qalcyo.json.util;

import xyz.qalcyo.json.entities.JsonElement;
import xyz.qalcyo.json.parser.JsonParser;

public class GsonHelper {

    public static JsonElement convert(com.google.gson.JsonElement element) {
        if (element == null)
            return null;
        return JsonParser.parse(element.toString());
    }

}