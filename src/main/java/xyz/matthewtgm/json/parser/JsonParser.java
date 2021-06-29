package xyz.matthewtgm.json.parser;

import xyz.matthewtgm.json.entities.JsonElement;

public class JsonParser {

    /**
     * @param input The JSON string to parse.
     * @return The parsed JSON element.
     * @author Danterus
     * @since 2.0
     */
    public static JsonElement parse(String input) {
        return JsonParserHelper.parse(input);
    }

}