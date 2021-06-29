package xyz.matthewtgm.json.parser;

import xyz.matthewtgm.json.entities.JsonArray;
import xyz.matthewtgm.json.entities.JsonElement;
import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.entities.JsonPrimitive;

import java.util.List;
import java.util.Map;

public class JsonParser {

    public static JsonElement parse(String input, Class<? extends JsonElement> type) {
        System.out.println(input);
        boolean object = type.isAssignableFrom(JsonObject.class);
        boolean array = type.isAssignableFrom(JsonArray.class);
        if (object && !input.startsWith("{")) JsonParserHelper.throwParseException("Expected the beginning of an object.");
        if (array && !input.startsWith("[")) JsonParserHelper.throwParseException("Expected the beginning of an array.");

        if (object) return JsonParserHelper.parseObject(input);
        if (array) return JsonParserHelper.parseArray(input);

        JsonParserHelper.throwParseException("Couldn't parse the input provided!");
        return null;
    }

    public static JsonElement parse(String input) {
        if (input.startsWith("{")) return parse(input, JsonObject.class);
        if (input.startsWith("[")) return parse(input, JsonArray.class);
        return null;
    }

    public static JsonElement parse(JsonElement element) {
        return parse(element.toString(), element.getClass());
    }

    public static JsonPrimitive parseJsonPrimitive(Object val) {
        if (val == null) val = "null";
        if (val instanceof String) val = "\"" + JsonParserHelper.escape(val.toString()) + "\"";
        if (val instanceof Double) val = JsonParserHelper.parseDecimalNumber(val);
        if (val instanceof Float) val = JsonParserHelper.parseDecimalNumber(val);
        if (val instanceof Number) val = val.toString();
        if (val instanceof JsonElement && !(val instanceof JsonPrimitive)) val = val.toString();
        if (val instanceof Map) val = parse(JsonParserHelper.createObjectString(val), JsonObject.class);
        if (val instanceof List) val = parse(JsonParserHelper.createArrayString(val), JsonObject.class);
        return new JsonPrimitive(val);
    }

}