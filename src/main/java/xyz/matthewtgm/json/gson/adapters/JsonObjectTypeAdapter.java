package xyz.matthewtgm.json.gson.adapters;

import com.google.gson.*;
import xyz.matthewtgm.json.objects.JsonObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class JsonObjectTypeAdapter implements JsonDeserializer<JsonObject>, JsonSerializer<JsonObject> {

    public JsonObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new JsonObject(new GsonBuilder().setLenient().create().fromJson(json.toString(), Map.class));
    }

    public JsonElement serialize(JsonObject src, Type typeOfSrc, JsonSerializationContext context) {
        return JsonParser.parseString(src.toJson());
    }

}