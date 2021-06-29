package xyz.matthewtgm.json.parser;

import xyz.matthewtgm.json.entities.JsonArray;
import xyz.matthewtgm.json.entities.JsonElement;
import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.entities.JsonPrimitive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParser {

    public static JsonElement parse(String input, Class<? extends JsonElement> type) {
        boolean object = type.isAssignableFrom(JsonObject.class);
        boolean array = type.isAssignableFrom(JsonArray.class);
        if (object && (!input.startsWith("{") || !input.endsWith("}"))) JsonParserHelper.throwParseException("Expected the beginning of an object.");
        if (array && (!input.startsWith("[") || !input.endsWith("]"))) JsonParserHelper.throwParseException("Expected the beginning of an array.");

        if (object) return JsonParserHelper.parseObject(input);
        if (array) return JsonParserHelper.parseArray(input);

        JsonParserHelper.throwParseException("Couldn't parse the input provided!");
        return null;
    }

    public static JsonElement parse(String input) {
        return JsonParserHelper.parse(input);
    }

    public static JsonElement parse(JsonElement element) {
        return parse(element.toString(), element.getClass());
    }

}