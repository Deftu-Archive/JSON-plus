package xyz.matthewtgm.json.gson.adapters;

import com.google.gson.*;
import xyz.matthewtgm.json.objects.JsonObject;
import xyz.matthewtgm.json.util.GlobalGson;

import java.lang.reflect.Type;
import java.util.Map;

public class JsonObjectTypeAdapter implements JsonDeserializer<JsonObject>, JsonSerializer<JsonObject> {

    public JsonObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new JsonObject(GlobalGson.getGson().fromJson(json.getAsString(), Map.class));
    }

    public JsonElement serialize(JsonObject src, Type typeOfSrc, JsonSerializationContext context) {
        return GlobalGson.getGson().fromJson(src.toJson(), com.google.gson.JsonObject.class);
    }

}