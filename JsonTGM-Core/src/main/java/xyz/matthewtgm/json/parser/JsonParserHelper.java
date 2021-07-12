package xyz.matthewtgm.json.parser;

import xyz.matthewtgm.json.adaptation.TypeAdapter;
import xyz.matthewtgm.json.entities.JsonArray;
import xyz.matthewtgm.json.entities.JsonElement;
import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.entities.JsonPrimitive;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParserHelper {

    private static final Map<Class<?>, TypeAdapter<?>> typeAdapters = new HashMap<>();

    /**
     * @param typeAdapter The type adapter to be registered.
     * @author MatthewTGM
     * @since 2.1
     */
    public static void registerTypeAdapter(Class<?> type, TypeAdapter<?> typeAdapter) {
        typeAdapters.put(type, typeAdapter);
    }

    /**
     * @param input The string to parse.
     * @return The parsed JSON element.
     * @author Danterus
     * @since 2.0
     */
    public static JsonElement parse(String input) {
        input = makeUnpretty(input);
        if (input.startsWith("{"))
            return parseObject(input);
        else if (input.startsWith("["))
            return parseArray(input);
        else
            return parsePrimitive(input);
    }

    /**
     * @param input The string to parse.
     * @return The parsed JSON object.
     * @author Danterus
     * @since 2.0
     */
    public static JsonObject parseObject(Object input) {
        if (typeAdapters.containsKey(input.getClass())) input = serializeTypeAdapter(typeAdapters.get(input.getClass()), input);
        String inputStr = input.toString();
        Map<String, JsonElement> elements = new HashMap<>();
        int position = 0;
        boolean isParsingKey = false;
        StringBuilder currentKey = new StringBuilder();
        boolean isParsingElement = false;
        StringBuilder currentElement = new StringBuilder();
        boolean isInString = false;

        for (char c : inputStr.toCharArray()) {
            if (isParsingKey)
                currentKey.append(c);
            if (isParsingElement)
                currentElement.append(c);
            boolean submitElement = false;
            if ((c == '{' || c == '[') && !isInString)
                position += 1;
            else if ((c == '}' || c == ']') && !isInString) {
                position -= 1;
                if (position == 0)
                    submitElement = true;
                if (position == 0 && isParsingElement)
                    currentElement.deleteCharAt(currentElement.length() - 1);
            } else if (c == '"') {
                if (position == 1) {
                    if (!isParsingKey && !isParsingElement)
                        isParsingKey = true;
                    else if (isParsingKey) {
                        isParsingKey = false;
                        currentKey.deleteCharAt(currentKey.length() - 1);
                    }
                }
                isInString = !isInString;
            } else if (c == ':' && !isParsingKey && !isParsingElement && !isInString) {
                if (position == 1)
                    isParsingElement = true;
            } else if (c == ',' && !isParsingKey && isParsingElement && !isInString) {
                if (position == 1) {
                    isParsingElement = false;
                    submitElement = true;
                    currentElement.deleteCharAt(currentElement.length() - 1);
                }
            }
            if (submitElement) {
                elements.put(currentKey.toString(), parse(currentElement.toString()));
                currentKey = new StringBuilder();
                currentElement = new StringBuilder();
            }
        }
        return new JsonObject(elements);
    }

    /**
     * @param input The string to parse.
     * @return The parsed JSON array.
     * @author Danterus
     * @since 2.0
     */
    public static JsonArray parseArray(Object input) {
        if (typeAdapters.containsKey(input.getClass())) input = serializeTypeAdapter(typeAdapters.get(input.getClass()), input);
        String inputStr = input.toString();
        List<JsonElement> elements = new ArrayList<>();
        int position = 0;
        StringBuilder currentElement = new StringBuilder();
        boolean isInString = false;

        for (char c : inputStr.toCharArray()) {
            currentElement.append(c);
            boolean submitElement = false;
            if ((c == '{' || c == '[') && !isInString) {
                position += 1;
                if (position == 1)
                    currentElement.deleteCharAt(currentElement.length() - 1);
            } else if ((c == '}' || c == ']') && !isInString) {
                position -= 1;
                if (position == 1 || position == 0)
                    submitElement = true;
                if (position == 0)
                    currentElement.deleteCharAt(currentElement.length() - 1);
            } else if (c == ',' && !isInString) {
                if (position == 1 || position == 0) {
                    submitElement = true;
                    currentElement.deleteCharAt(currentElement.length() - 1);
                }
            } else if (c == '"') {
                isInString = !isInString;
            }
            if (submitElement) {
                elements.add(parse(currentElement.toString()));
                currentElement = new StringBuilder();
            }
        }
        return new JsonArray(elements);
    }

    /**
     * @param input The string to parse.
     * @return The parsed JSON primitive.
     * @author Danterus
     * @since 2.0
     */
    public static JsonPrimitive parsePrimitive(Object input) {
        if (typeAdapters.containsKey(input.getClass())) input = serializeTypeAdapter(typeAdapters.get(input.getClass()), input);
        String inputStr = input.toString();
        Object value = null;
        Object number = parseDecimalNumber(inputStr);
        if (!inputStr.isEmpty()) {
            if (number != null)
                value = number;
            else if (inputStr.startsWith("\"") && inputStr.endsWith("\""))
                value = inputStr.substring(1, inputStr.length() - 1);
        }
        Boolean bool = parseBoolean(inputStr);
        if(bool != null) {
            value = bool;
        }
        return new JsonPrimitive(value == null ? input : value);
    }

    public static <T> JsonElement serializeTypeAdapter(TypeAdapter<T> typeAdapter, Object source) {
        return typeAdapter.serialize((T) source);
    }

    public static <T> T deserializeTypeAdapter(TypeAdapter<T> typeAdapter, JsonElement element) {
        return typeAdapter.deserialize(element);
    }

    /**
     * @param input The string to parse.
     * @return The parsed decimal number.
     * @author MatthewTGM
     * @since 2.0
     */
    public static Object parseDecimalNumber(String input) {
        try {
            return Float.parseFloat(input);
        } catch (Exception ignored) {}

        try {
            return Double.parseDouble(input);
        } catch (Exception ignored) {}
        return null;
    }

    public static Boolean parseBoolean(String input) {
        if(input.equals("true")) {
            return true;
        } else if(input.equals("false")) {
            return false;
        }
        return null;
    }

    /**
     * @param json The JSON to unprettify.
     * @return The unprettified JSON string.
     * @author Danterus
     * @since 2.0
     */
    private static String makeUnpretty(String json) {
        StringBuilder result = new StringBuilder();
        boolean isInQuote = false;

        for (char c : json.toCharArray()) {
            if (c == '"') isInQuote = !isInQuote;
            if (isInQuote || !(c == '\n' || c == ' ')) result.append(c);
        }
        return result.toString();
    }

    /**
     * @return The list of all registered type adapters.
     * @author MatthewTGM
     * @since 2.1
     */
    public static Map<Class<?>, TypeAdapter<?>> getTypeAdapters() {
        return typeAdapters;
    }

}