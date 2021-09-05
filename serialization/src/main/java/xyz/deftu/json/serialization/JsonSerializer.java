package xyz.deftu.json.serialization;

import xyz.deftu.json.entities.JsonElement;
import xyz.deftu.json.entities.JsonObject;
import xyz.deftu.json.serialization.annotations.JsonSerialize;
import xyz.deftu.json.serialization.annotations.JsonSerializeExcluded;
import xyz.deftu.json.serialization.annotations.JsonSerializeName;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class JsonSerializer {

    private JsonSerializer() {}

    /**
     * Serializes a class to a file.
     * @param instance An instance of the class.
     * @param type The type of the class.
     * @author MatthewTGM
     * @since 1.9
     */
    public static void serialize(Object instance, Class<?> type) {
        if (type.isAnnotationPresent(JsonSerialize.class)) {
            JsonSerialize serialize = type.getAnnotation(JsonSerialize.class);
            write(fixFileName(serialize.value()), jsonify(instance, type), parent(new File(serialize.value())), serialize.pretty());
        } else throw new IllegalStateException("The class provided isn't meant to be serialized! ( " + type.getSimpleName() + " )");
    }

    /**
     * @param instance An instance of the class.
     * @param type The type of the class.
     * @return A JSON object populated by the fields in the class.
     * @author MatthewTGM
     * @since 1.9
     */
    public static JsonObject create(Object instance, Class<?> type) {
        return jsonify(instance, type);
    }

    /**
     * @param instance An instance of the class.
     * @return A JSON object populated by the fields in the class.
     * @author MatthewTGM
     * @since 1.9
     */
    public static JsonObject create(Object instance) {
        return create(instance, instance.getClass());
    }

    private static JsonObject jsonify(Object instance, Class<?> type) {
        JsonObject json = new JsonObject();
        try {
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(JsonSerializeExcluded.class))
                    continue;
                String name = field.getName();
                if (field.isAnnotationPresent(JsonSerializeName.class))
                    name = field.getAnnotation(JsonSerializeName.class).value();
                Object value = field.get(instance);
                if (value == null)
                    continue;
                if (checkEmpty(value) == null)
                    continue;
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
            if (invokeClazz.getSimpleName().toLowerCase().contains("bool"))
                val = invoke;
            else val = null;
        } catch (Exception e) {
            e.printStackTrace();
            val = null;
        }
        return val;
    }

    private static String fixFileName(String fileName) {
        if (fileName.endsWith(".json"))
            fileName = fileName.substring(0, fileName.indexOf(".json"));
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

    private static void write(String fileName, JsonElement element, File directory, boolean pretty) {
        if (fileName.endsWith(".json"))
            fileName = fileName.substring(0, fileName.indexOf(".json"));
        if (directory == null)
            directory = new File("./");
        BufferedWriter writer = null;
        try {
            if (!directory.exists() && !directory.mkdirs())
                throw new IllegalStateException("Directory didn't exist, failed to create it.");
            File file = new File(directory, fileName + ".json");
            if (!file.exists() && !file.createNewFile())
                throw new IllegalStateException("Failed to create JSON file.");
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(pretty ? makePretty(element.toString(), 4) : element.toString());
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                } else
                    System.err.println("Writer was null!");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                } else
                    System.err.println("Writer was null!");
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    private static String makePretty(String json, int indent) {
        StringBuilder result = new StringBuilder();
        boolean isInQuote = false;
        int currentIndent = 0;
        for(char c : makeUnpretty(json).toCharArray()) {
            if(c == '"')
                isInQuote = !isInQuote;
            if(!isInQuote && (c == '{' || c == '[')) {
                currentIndent += indent;
                result.append(c);
                result.append('\n');
                for(int i = 0; i < currentIndent; i++)
                    result.append(" ");
            } else if(!isInQuote && (c == '}' || c == ']')) {
                currentIndent -= indent;
                result.append('\n');
                for(int i = 0; i < currentIndent; i++)
                    result.append(" ");
                result.append(c);
            } else if(!isInQuote && c == ',') {
                result.append(c).append('\n');
                for(int i = 0; i < currentIndent; i++)
                    result.append(" ");
            } else if(!isInQuote && c == ':')
                result.append(c).append(" ");
            else result.append(c);
        }
        return result.toString();
    }

    private static String makeUnpretty(String json) {
        StringBuilder result = new StringBuilder();
        boolean isInQuote = false;

        for(char c : json.toCharArray()) {
            if(c == '"')
                isInQuote = !isInQuote;
            if(isInQuote || !(c == '\n' || c == ' '))
                result.append(c);
        }
        return result.toString();
    }

}