package xyz.matthewtgm.json.parser;

import xyz.matthewtgm.json.entities.JsonArray;
import xyz.matthewtgm.json.entities.JsonElement;
import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.entities.JsonPrimitive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParser {

    public static JsonElement parse(String input, Class<? extends JsonElement> type) {
        boolean object = type.isAssignableFrom(JsonObject.class);
        boolean array = type.isAssignableFrom(JsonArray.class);
        if (object && (!input.startsWith("{") || !input.endsWith("}"))) JsonParserHelper.throwParseException("Expected the beginning of an object.");
        if (array && (!input.startsWith("[") || !input.endsWith("]"))) JsonParserHelper.throwParseException("Expected the beginning of an array.");

        if (object) return JsonParserHelper.parseObject(input);
        if (array) return JsonParserHelper.parseArray(input);

        JsonParserHelper.throwParseException("Couldn't parse the input provided!");
        return null;
    }

    public static JsonElement parse(String input) {
        input = makeUnpretty(input);
        if(input.startsWith("{")) {
            Map<String, JsonElement> elements = new HashMap<>();

            int position = 0;
            boolean isParsingKey = false;
            StringBuilder currentKey = new StringBuilder();
            boolean isParsingElement = false;
            StringBuilder currentElement = new StringBuilder();
            boolean isInString = false;

            for(char c : input.toCharArray()) {
                if(isParsingKey) {
                    currentKey.append(c);
                }
                if(isParsingElement) {
                    currentElement.append(c);
                }

                boolean submitElement = false;
                if((c == '{' || c == '[') && !isInString) {
                    position += 1;
                } else if((c == '}' || c == ']') && !isInString) {
                    position -= 1;
                    if(position == 1 || position == 0) {
                        submitElement = true;
                    }
                    if(position == 0 && isParsingElement) {
                        currentElement.deleteCharAt(currentElement.length() - 1);
                    }
                } else if(c == '"') {
                    if(position == 1) {
                        if (!isParsingKey && !isParsingElement) {
                            isParsingKey = true;
                        } else if (isParsingKey) {
                            isParsingKey = false;
                            currentKey.deleteCharAt(currentKey.length() - 1);
                        }
                    }
                    isInString = !isInString;
                } else if(c == ':' && !isParsingKey && !isParsingElement && !isInString) {
                    if(position == 1) {
                        isParsingElement = true;
                    }
                } else if(c == ',' && !isParsingKey && isParsingElement && !isInString) {
                    if(position == 1) {
                        isParsingElement = false;
                        submitElement = true;
                        currentElement.deleteCharAt(currentElement.length() - 1);
                    }
                }

                if(submitElement) {
                    elements.put(currentKey.toString(), parse(currentElement.toString()));
                    currentKey = new StringBuilder();
                    currentElement = new StringBuilder();
                }
            }

            return new JsonObject(elements);
        } else if(input.startsWith("[")) {
            List<JsonElement> elements = new ArrayList<>();

            int position = 0;
            StringBuilder currentElement = new StringBuilder();

            for(char c : input.toCharArray()) {
                currentElement.append(c);

                boolean submitElement = false;
                if(c == '{' || c == '[') {
                    position += 1;
                    if(position == 1) {
                        currentElement.deleteCharAt(currentElement.length() - 1);
                    }
                } else if(c == '}' || c == ']') {
                    position -= 1;
                    if(position == 1 || position == 0) {
                        submitElement = true;
                    }
                    if(position == 0) {
                        currentElement.deleteCharAt(currentElement.length() - 1);
                    }
                } else if(c == ',') {
                    if(position == 1) {
                        submitElement = true;
                        currentElement.deleteCharAt(currentElement.length() - 1);
                    }
                }

                if(submitElement) {
                    elements.add(parse(currentElement.toString()));
                    currentElement = new StringBuilder();
                }
            }

            return new JsonArray(elements);
        } else {
            if(input.startsWith("\"")) {
                return new JsonPrimitive(input.substring(1, input.length() - 1));
            }
        }
        if (input.startsWith("{") && input.endsWith("}")) return parse(input, JsonObject.class);
        if (input.startsWith("[") && input.endsWith("]")) return parse(input, JsonArray.class);
        return null;
    }

    private static String makeUnpretty(String json) {
        StringBuilder result = new StringBuilder();
        boolean isInQuote = false;
        for(char c : json.toCharArray()) {
            if(c == '"') {
                isInQuote = !isInQuote;
            }
            if(isInQuote || !(c == '\n' || c == ' ')) {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static JsonElement parse(JsonElement element) {
        return parse(element.toString(), element.getClass());
    }

}