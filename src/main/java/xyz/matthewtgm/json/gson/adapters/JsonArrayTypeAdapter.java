package xyz.matthewtgm.json.gson.adapters;

import com.google.gson.*;
import xyz.matthewtgm.json.objects.JsonArray;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class JsonArrayTypeAdapter implements JsonDeserializer<JsonArray>, JsonSerializer<JsonArray> {

    public JsonArray deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new JsonArray(convertToList(json.toString()));
    }

    public JsonElement serialize(JsonArray src, Type typeOfSrc, JsonSerializationContext context) {
        return JsonParser.parseString(src.toJson());
    }

    private List convertToList(String input) {
        return Arrays.asList(input);
    }

}