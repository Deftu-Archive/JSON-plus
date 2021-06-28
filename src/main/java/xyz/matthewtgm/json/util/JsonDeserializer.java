package xyz.matthewtgm.json.util;

import xyz.matthewtgm.json.annotations.JsonSerializeExcluded;
import xyz.matthewtgm.json.annotations.JsonSerializeName;
import xyz.matthewtgm.json.objects.JsonObject;

import java.lang.reflect.Field;

public class JsonDeserializer {

    public static <T> T deserialize(String json, Class<T> type) {
        T value;
        try {
            value = type.newInstance();
            for (Field field : value.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(JsonSerializeExcluded.class)) continue;
                String name = field.getName();
                if (field.isAnnotationPresent(JsonSerializeName.class)) name = field.getAnnotation(JsonSerializeName.class).value();

                JsonObject<String, Object> parsedJson = JsonHelper.getJsonType(json);
                if (parsedJson != null && parsedJson.containsKey(name)) field.set(value, parsedJson.get(name));
            }
        } catch (Exception e) {
            e.printStackTrace();
            value = null;
        }
        return value;
    }

    public static <T> T deserialize(JsonObject<String, Object> json, Class<T> type) {
        return deserialize(json.toJson(), type);
    }

}