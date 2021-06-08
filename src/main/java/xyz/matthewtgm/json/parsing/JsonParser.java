package xyz.matthewtgm.json.parsing;

import com.google.gson.Gson;
import xyz.matthewtgm.json.base.Json;
import xyz.matthewtgm.json.objects.JsonArray;
import xyz.matthewtgm.json.objects.JsonObject;

public class JsonParser {

    private static final Gson gson = new Gson();

    public static JsonObject<?, ?> parseObj(String s) {
        return gson.fromJson(s, JsonObject.class);
    }

    public static JsonArray<?> parseArr(String s) {
        return gson.fromJson(s, JsonArray.class);
    }

    public static Json parse(String s, Class<?> jsonClazz) {
        if(jsonClazz.isAssignableFrom(JsonObject.class)) return gson.fromJson(s, JsonObject.class);
        if(jsonClazz.isAssignableFrom(JsonArray.class)) return gson.fromJson(s, JsonArray.class);
        return null;
    }

}