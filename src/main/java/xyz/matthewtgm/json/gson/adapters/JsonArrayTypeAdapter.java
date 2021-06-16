package xyz.matthewtgm.json.gson.adapters;

import com.google.gson.*;
import xyz.matthewtgm.json.objects.JsonArray;
import xyz.matthewtgm.json.util.GlobalGson;

import java.lang.reflect.Type;
import java.util.List;

public class JsonArrayTypeAdapter implements JsonDeserializer<JsonArray>, JsonSerializer<JsonArray> {

    public JsonArray deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return xyz.matthewtgm.json.parsing.JsonParser.parseArr(json.getAsString());
    }

    public JsonElement serialize(JsonArray src, Type typeOfSrc, JsonSerializationContext context) {
        return JsonParser.parseString(src.toJson());
    }

}