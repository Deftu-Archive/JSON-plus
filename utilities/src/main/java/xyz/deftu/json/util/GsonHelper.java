package xyz.deftu.json.util;

import xyz.deftu.json.entities.JsonElement;
import xyz.deftu.json.parser.JsonParser;

public class GsonHelper {

    public static JsonElement convert(com.google.gson.JsonElement element) {
        if (element == null)
            return null;
        return JsonParser.parse(element.toString());
    }

}