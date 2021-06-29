package xyz.matthewtgm.json.parser;

import xyz.matthewtgm.json.entities.JsonArray;
import xyz.matthewtgm.json.entities.JsonElement;
import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.entities.JsonPrimitive;
import xyz.matthewtgm.json.exceptions.JsonParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParserHelper {

    public static void throwParseException(Object o) {
        try {
            if (o == null)
                throw new JsonParseException();
            if (o instanceof Throwable)
                throw new JsonParseException((Throwable) o);
            if (o instanceof String)
                throw new JsonParseException(o.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JsonElement parse(String input) {
        input = makeUnpretty(input);
        if (input.startsWith("{"))
            return parseObject(input);
        else if (input.startsWith("["))
            return parseArray(input);
        else
            return parsePrimitive(input);
    }

    public static JsonObject parseObject(String input) {
        Map<String, JsonElement> elements = new HashMap<>();
        int position = 0;
        boolean isParsingKey = false;
        StringBuilder currentKey = new StringBuilder();
        boolean isParsingElement = false;
        StringBuilder currentElement = new StringBuilder();
        boolean isInString = false;

        for (char c : input.toCharArray()) {
            if (isParsingKey)
                currentKey.append(c);
            if (isParsingElement)
                currentElement.append(c);
            boolean submitElement = false;
            if ((c == '{' || c == '[') && !isInString)
                position += 1;
            else if ((c == '}' || c == ']') && !isInString) {
                position -= 1;
                if (position == 1 || position == 0)
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

    public static JsonArray parseArray(String input) {
        List<JsonElement> elements = new ArrayList<>();
        int position = 0;
        StringBuilder currentElement = new StringBuilder();

        for (char c : input.toCharArray()) {
            currentElement.append(c);
            boolean submitElement = false;
            if (c == '{' || c == '[') {
                position += 1;
                if (position == 1)
                    currentElement.deleteCharAt(currentElement.length() - 1);
            } else if (c == '}' || c == ']') {
                position -= 1;
                if (position == 1 || position == 0)
                    submitElement = true;
                if (position == 0)
                    currentElement.deleteCharAt(currentElement.length() - 1);
            } else if (c == ',') {
                if (position == 1) {
                    submitElement = true;
                    currentElement.deleteCharAt(currentElement.length() - 1);
                }
            }
            if (submitElement) {
                elements.add(parse(currentElement.toString()));
                currentElement = new StringBuilder();
            }
        }
        return new JsonArray(elements);
    }

    public static JsonPrimitive parsePrimitive(String input) {
        Object value = null;
        Object number = parseDecimalNumber(input);
        if (number != null)
            value = number;
        else if (!input.isEmpty())
            value = input.substring(1, input.length() - 1);
        if (value == null) return new JsonPrimitive("null");
        return new JsonPrimitive(value);
    }

    public static Object parseDecimalNumber(String input) {
        try {
            return Float.parseFloat(input);
        } catch (Exception ignored) {}

        try {
            return Double.parseDouble(input);
        } catch (Exception ignored) {}
        return null;
    }

    private static String makeUnpretty(String json) {
        StringBuilder result = new StringBuilder();
        boolean isInQuote = false;

        for (char c : json.toCharArray()) {
            if (c == '"') isInQuote = !isInQuote;
            if (isInQuote || !(c == '\n' || c == ' ')) result.append(c);
        }
        return result.toString();
    }

}