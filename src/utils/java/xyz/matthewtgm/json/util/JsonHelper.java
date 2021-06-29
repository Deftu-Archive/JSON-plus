package xyz.matthewtgm.json.util;

import xyz.matthewtgm.json.entities.JsonElement;
import xyz.matthewtgm.json.parser.JsonParser;

public class JsonHelper {

    public static String makePretty(String json, int indent) {
        StringBuilder result = new StringBuilder();
        boolean isInQuote = false;
        int currentIndent = 0;
        for(char c : makeUnpretty(json).toCharArray()) {
            if(c == '"') {
                isInQuote = !isInQuote;
            }

            if(!isInQuote && (c == '{' || c == '[')) {
                currentIndent += indent;

                result.append(c);
                result.append('\n');
                for(int i = 0; i < currentIndent; i++) {
                    result.append(" ");
                }
            } else if(!isInQuote && (c == '}' || c == ']')) {
                currentIndent -= indent;

                result.append('\n');
                for(int i = 0; i < currentIndent; i++) {
                    result.append(" ");
                }
                result.append(c);
            } else if(!isInQuote && c == ',') {
                result.append(c).append('\n');
                for(int i = 0; i < currentIndent; i++) {
                    result.append(" ");
                }
            } else if(!isInQuote && c == ':') {
                result.append(c).append(" ");
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String makeUnpretty(String json) {
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

    public static boolean isValidJson(Object o) {
        String str = o.toString();
        boolean valid = false;

        try {
            JsonElement element = JsonParser.parse(str);
            if (element != null) valid = true;
        } catch (Exception ignored) {}

        return valid;
    }

}