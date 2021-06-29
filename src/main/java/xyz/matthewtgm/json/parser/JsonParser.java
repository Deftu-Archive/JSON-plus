package xyz.matthewtgm.json.parser;

import xyz.matthewtgm.json.entities.JsonElement;

public class JsonParser {

    public static JsonElement parse(String input) {
        return JsonParserHelper.parse(input);
    }

    public static JsonElement parse(JsonElement element) {
        return parse(element.toString());
    }

}