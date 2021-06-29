package xyz.matthewtgm.json.serialization;

import xyz.matthewtgm.json.serialization.annotations.JsonSerializeExcluded;
import xyz.matthewtgm.json.serialization.annotations.JsonSerializeName;
import xyz.matthewtgm.json.objects.JsonArray;
import xyz.matthewtgm.json.objects.JsonObject;
import xyz.matthewtgm.json.util.JsonHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

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
                if (parsedJson != null && parsedJson.containsKey(name)) {
                    forceNotFinal(field);
                    Object val = parsedJson.get(name);
                    if (val instanceof Map) val = new JsonObject<>((Map) val);
                    if (val instanceof List) val = new JsonArray<>((List) val);
                    if (val instanceof Double && field.getType().isAssignableFrom(Integer.class) || field.getType().isAssignableFrom(int.class)) val = (int) Math.round((double) val);
                    field.set(value, val);
                }
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

    private static void forceNotFinal(Field field) throws Exception {
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
    }

}