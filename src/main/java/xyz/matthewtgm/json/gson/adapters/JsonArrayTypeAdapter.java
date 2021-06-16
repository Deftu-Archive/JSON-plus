package xyz.matthewtgm.json.gson.adapters;

import com.google.gson.*;
import xyz.matthewtgm.json.objects.JsonArray;
import xyz.matthewtgm.json.util.GlobalGson;

import java.lang.reflect.Type;
import java.util.List;

public class JsonArrayTypeAdapter implements JsonDeserializer<JsonArray>, JsonSerializer<JsonArray> {

    public JsonArray deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new JsonArray(GlobalGson.getGson().fromJson(json.getAsString(), List.class));
    }

    public JsonElement serialize(JsonArray src, Type typeOfSrc, JsonSerializationContext context) {
        return GlobalGson.getGson().fromJson(src.toJson(), com.google.gson.JsonArray.class);
    }

}