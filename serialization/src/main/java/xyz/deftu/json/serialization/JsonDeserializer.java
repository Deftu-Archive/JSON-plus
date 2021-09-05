package xyz.deftu.json.serialization;

import xyz.deftu.json.entities.JsonArray;
import xyz.deftu.json.entities.JsonObject;
import xyz.deftu.json.entities.JsonPrimitive;
import xyz.deftu.json.parser.JsonParser;
import xyz.deftu.json.serialization.annotations.JsonSerializeExcluded;
import xyz.deftu.json.serialization.annotations.JsonSerializeName;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonDeserializer {

    private JsonDeserializer() {}

    /**
     * @param json The JSON string to get all elements from.
     * @param type The type of the class.
     * @return An instance of the class with all of its respective fields set to the input's elements.
     * @author MatthewTGM
     * @since 1.10
     */
    public static <T> T deserialize(String json, Class<T> type) {
        T value;
        try {
            value = createClassInstance(type);
            for (Field field : value.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(JsonSerializeExcluded.class)) continue;
                String name = field.getName();
                if (field.isAnnotationPresent(JsonSerializeName.class)) name = field.getAnnotation(JsonSerializeName.class).value();

                JsonObject parsedJson = (JsonObject) JsonParser.parse(json);
                if (parsedJson != null && parsedJson.hasKey(name)) {
                    forceNotFinal(field);
                    Object val = ((JsonPrimitive) parsedJson.get(name)).getValue();
                    if (val instanceof Map) val = new JsonObject((Map) val);
                    if (val instanceof List) val = new JsonArray((List) val);
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

    /**
     * @param json The JSON to get all elements from.
     * @param type The type of the class.
     * @return An instance of the class with all of it's respective fields set to the input's elements.
     * @author MatthewTGM
     * @since 1.10
     */
    public static <T> T deserialize(JsonObject json, Class<T> type) {
        return deserialize(json.toString(), type);
    }

    private static <T> T createClassInstance(Class<T> clazz) throws Exception {
        List<Object> parameters = new ArrayList<>();
        Constructor<T> constructor = (Constructor<T>) clazz.getDeclaredConstructors()[0];
        for (Class<?> parameterType : constructor.getParameterTypes()) parameters.add(null);
        return constructor.newInstance(parameters.toArray());
    }

    private static void forceNotFinal(Field field) throws Exception {
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
    }

}