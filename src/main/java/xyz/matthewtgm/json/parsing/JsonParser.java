package xyz.matthewtgm.json.parsing;

import xyz.matthewtgm.json.base.Json;
import xyz.matthewtgm.json.objects.JsonArray;
import xyz.matthewtgm.json.objects.JsonObject;
import xyz.matthewtgm.json.util.GlobalGson;

public class JsonParser {

    public static <K extends String, V> JsonObject<K, V> parseObj(String s) {
        return GlobalGson.getGson().fromJson(s, JsonObject.class);
    }

    public static <T> JsonArray<T> parseArr(String s) {
        return GlobalGson.getGson().fromJson(s, JsonArray.class);
    }

    public static Json parse(String s, Class<?> jsonClazz) {
        if(jsonClazz.isAssignableFrom(JsonObject.class)) return GlobalGson.getGson().fromJson(s, JsonObject.class);
        if(jsonClazz.isAssignableFrom(JsonArray.class)) return GlobalGson.getGson().fromJson(s, JsonArray.class);
        return null;
    }

}