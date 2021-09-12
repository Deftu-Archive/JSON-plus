package xyz.qalcyo.json.util;

import xyz.qalcyo.json.entities.JsonArray;
import xyz.qalcyo.json.entities.JsonObject;
import xyz.qalcyo.json.parser.JsonParser;
import xyz.qalcyo.json.entities.JsonElement;

import java.util.concurrent.atomic.AtomicBoolean;

public class JsonHelper {

    public static int DEFAULT_INDENT = 4;

    private JsonHelper() {}

    public static boolean deepSearchKey(String key, JsonElement element) {
        if (element instanceof JsonObject) {
            JsonObject object = (JsonObject) element;
            AtomicBoolean value = new AtomicBoolean(false);
            object.forEach((elementKey, objectElement) -> {
                boolean found = elementKey.equals(key);
                if (!found && (objectElement instanceof JsonObject || objectElement instanceof JsonArray)) found = deepSearchKey(key, objectElement);
                value.set(found);
            });
            return value.get();
        }
        if (element instanceof JsonArray) {
            JsonArray array = (JsonArray) element;
            AtomicBoolean value = new AtomicBoolean(false);
            array.forEach(arrayElement -> {
                boolean found = arrayElement.getAsString().equals(key);
                if (!found && (arrayElement instanceof JsonObject || arrayElement instanceof JsonArray)) found = deepSearchKey(key, arrayElement);
                value.set(found);
            });
            return value.get();
        }
        return false;
    }

    public static boolean deepSearchValue(JsonElement value, JsonElement element) {
        if (element instanceof JsonObject) {
            JsonObject object = (JsonObject) element;
            AtomicBoolean returnValue = new AtomicBoolean(false);
            object.forEach((elementKey, objectElement) -> {
                boolean found = objectElement.getAsString().equals(value.getAsString());
                if (!found && (objectElement instanceof JsonObject || objectElement instanceof JsonArray)) found = deepSearchValue(value, objectElement);
                returnValue.set(found);
            });
            return returnValue.get();
        }
        if (element instanceof JsonArray) {
            JsonArray array = (JsonArray) element;
            AtomicBoolean returnValue = new AtomicBoolean(false);
            array.forEach(arrayElement -> {
                boolean found = arrayElement.getAsString().equals(value.getAsString());
                if (!found && (arrayElement instanceof JsonObject || arrayElement instanceof JsonArray)) found = deepSearchValue(value, arrayElement);
                returnValue.set(found);
            });
            return returnValue.get();
        }
        return false;
    }

    public static boolean deepSearch(Object o, JsonElement element) {
        return deepSearchKey(o.toString(), element) || deepSearchValue((JsonElement) o, element);
    }

    /**
     * @param json The JSON string to prettify.
     * @param indent The indent level.
     * @return A prettified version of the input JSON.
     * @author Danterus
     * @since 2.0
     */
    public static String makePretty(String json, int indent) {
        StringBuilder result = new StringBuilder();
        boolean isInQuote = false;
        int currentIndent = 0;

        for(char c : makeUnpretty(json).toCharArray()) {
            if(c == '"') isInQuote = !isInQuote;
            if(!isInQuote && (c == '{' || c == '[')) {
                currentIndent += indent;
                result.append(c);
                result.append('\n');
                for(int i = 0; i < currentIndent; i++) result.append(" ");
            } else if(!isInQuote && (c == '}' || c == ']')) {
                currentIndent -= indent;
                result.append('\n');
                for(int i = 0; i < currentIndent; i++) result.append(" ");
                result.append(c);
            } else if(!isInQuote && c == ',') {
                result.append(c).append('\n');
                for(int i = 0; i < currentIndent; i++) result.append(" ");
            } else if(!isInQuote && c == ':') result.append(c).append(" ");
            else result.append(c);
        }
        return result.toString();
    }

    /**
     * @param element The JSON element to prettify.
     * @param indent The indent level.
     * @return A prettified version of the input JSON.
     * @author Danterus
     * @since 2.0
     */
    public static String makePretty(JsonElement element, int indent) {
        return makePretty(element.toString(), indent);
    }

    /**
     * @param json The JSON string to prettify.
     * @return A prettified version of the input JSON.
     * @author MatthewTGM
     * @since 2.8
     */
    public static String makePretty(String json) {
        return makePretty(json, DEFAULT_INDENT);
    }

    /**
     * @param element The JSON element to prettify.
     * @return A prettified version of the input JSON.
     * @author MatthewTGM
     * @since 2.8
     */
    public static String makePretty(JsonElement element) {
        return makePretty(element, DEFAULT_INDENT);
    }

    /**
     * @param json The JSON to unprettify.
     * @return Unprettified JSON.
     */
    public static String makeUnpretty(String json) {
        StringBuilder result = new StringBuilder();
        boolean isInQuote = false;

        for(char c : json.toCharArray()) {
            if(c == '"') isInQuote = !isInQuote;
            if(isInQuote || !(c == '\n' || c == ' ')) result.append(c);
        }
        return result.toString();
    }

    /**
     * @param o The object to check for validity.
     * @return Whether or not the object provided is valid JSON.
     */
    public static boolean isValidJson(Object o) {
        JsonElement parsed = JsonParser.parse(o.toString());
        return parsed.isJsonObject() || parsed.isJsonArray();
    }

}