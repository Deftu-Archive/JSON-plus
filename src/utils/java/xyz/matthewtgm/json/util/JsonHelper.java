package xyz.matthewtgm.json.util;

import xyz.matthewtgm.json.entities.JsonElement;
import xyz.matthewtgm.json.parser.JsonParser;

public class JsonHelper {

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
        String str = o.toString();
        boolean valid = false;

        // TODO: 2021/06/30 : Needs improvement.

        if (str.startsWith("{") && str.endsWith("}")) valid = true;
        if (str.startsWith("[") && str.endsWith("]")) valid = true;

        return valid;
    }

}