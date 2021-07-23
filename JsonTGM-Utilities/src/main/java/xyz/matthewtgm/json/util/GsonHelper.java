package xyz.matthewtgm.json.util;

import xyz.matthewtgm.json.parser.JsonParser;

public class GsonHelper {

    public static xyz.matthewtgm.json.entities.JsonElement convert(com.google.gson.JsonElement element) {
        if (element == null)
            return null;
        return JsonParser.parse(element.toString());
    }

}