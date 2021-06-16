package xyz.matthewtgm.json.gson.adapters;

import com.google.gson.*;
import xyz.matthewtgm.json.objects.JsonObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class JsonObjectTypeAdapter implements JsonDeserializer<JsonObject>, JsonSerializer<JsonObject> {

    public JsonObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new JsonObject(convertToMap(json.toString()));
    }

    public JsonElement serialize(JsonObject src, Type typeOfSrc, JsonSerializationContext context) {
        return JsonParser.parseString(src.toJson());
    }

    private Map convertToMap(String input) {
        input = input.substring(1, input.length()-1);
        String[] keyValuePairs = input.split(",");
        Map<String,String> map = new HashMap<>();
        for(String pair : keyValuePairs) {
            String[] entry = pair.split("=");
            map.put(entry[0].trim(), entry[1].trim());
        }
        return map;
    }

}