package xyz.matthewtgm.json.parsing;

import com.google.gson.Gson;
import xyz.matthewtgm.json.base.Json;
import xyz.matthewtgm.json.objects.JsonArray;
import xyz.matthewtgm.json.objects.JsonObject;

public class JsonParser {

    public static JsonObject parseObj(String s) {
        final Gson gson = new Gson();
        return (JsonObject) gson.fromJson(s, JsonObject.class);
    }

    public static JsonArray parseArr(String s) {
        final Gson gson = new Gson();
        return (JsonArray) gson.fromJson(s, JsonArray.class);
    }

    public static Json parse(String s, Class<?> jsonClazz) {
        Gson gson = new Gson();
        if(jsonClazz.isAssignableFrom(JsonObject.class)) {
            return gson.fromJson(s, JsonObject.class);
        }

        if(jsonClazz.isAssignableFrom(JsonArray.class)) {
            return gson.fromJson(s, JsonArray.class);
        }

        return null;
    }

}