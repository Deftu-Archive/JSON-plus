package xyz.matthewtgm.json.util;

import xyz.matthewtgm.json.annotations.JsonSerialize;
import xyz.matthewtgm.json.annotations.JsonSerializeExcluded;
import xyz.matthewtgm.json.annotations.JsonSerializeName;
import xyz.matthewtgm.json.files.JsonWriter;
import xyz.matthewtgm.json.objects.JsonObject;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class JsonSerializer {

    public static void serialize(Object instance, Class<?> type) {
        if (type.isAnnotationPresent(JsonSerialize.class)) {
            JsonSerialize serialize = type.getAnnotation(JsonSerialize.class);
            JsonWriter.write(fixFileName(serialize.value()), jsonify(instance, type), parent(new File(serialize.value())), serialize.pretty());
        } else throw new IllegalStateException("The class provided isn't meant to be serialized! ( " + type.getSimpleName() + " )");
    }

    public static void serialize(Object instance) {
        serialize(instance, instance.getClass());
    }

    public static JsonObject<String, Object> create(Object instance, Class<?> type) {
        return jsonify(instance, type);
    }

    public static JsonObject<String, Object> create(Object instance) {
        return create(instance, instance.getClass());
    }

    private static JsonObject<String, Object> jsonify(Object instance, Class<?> type) {
        JsonObject<String, Object> json = new JsonObject<>();
        try {
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(JsonSerializeExcluded.class)) continue;

                String name = field.getName();
                if (field.isAnnotationPresent(JsonSerializeName.class)) name = field.getAnnotation(JsonSerializeName.class).value();
                Object value = field.get(instance);
                if (value == null) continue;
                if (checkEmpty(value) == null) continue;
                json.add(name, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    private static Object checkEmpty(Object value) {
        Object val;
        try {
            Class<?> valueClazz = value.getClass();
            Method emptyMethod = valueClazz.getDeclaredMethod("isEmpty");
            Object invoke = emptyMethod.invoke(value);
            Class<?> invokeClazz = invoke.getClass();
            if (invokeClazz.getSimpleName().toLowerCase().contains("bool")) val = invoke;
            else val = null;
        } catch (Exception e) {
            e.printStackTrace();
            val = null;
        }
        return val;
    }

    private static String fixFileName(String fileName) {
        if (fileName.endsWith(".json")) fileName = fileName.substring(0, fileName.indexOf(".json"));
        return fileName;
    }

    private static File parent(File file) {
        File parent = file.getParentFile();
        if (parent == null)
            parent = new File("./");
        if (!parent.exists())
            parent.mkdirs();
        return parent;
    }

}