package xyz.deftu.json.parser;

import xyz.deftu.json.adaptation.TypeAdapter;
import xyz.deftu.json.entities.JsonElement;

import java.util.Map;

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

    /**
     * @return The element deserialized to the variable provided.
     * @author MatthewTGM
     * @since 2.3
     */
    public static <T> T deserialize(Class<T> type, JsonElement element) {
        return (T) JsonParserHelper.deserializeTypeAdapter(JsonParserHelper.getTypeAdapters().get(type), element);
    }

    /**
     * @param typeAdapter The type adapter to be registered.
     * @author MatthewTGM
     * @since 2.1
     */
    public static void registerTypeAdapter(Class<?> type, TypeAdapter<?> typeAdapter) {
        JsonParserHelper.registerTypeAdapter(type, typeAdapter);
    }

    /**
     * @return The list of all registered type adapters.
     * @author MatthewTGM
     * @since 2.1
     */
    public static Map<Class<?>, TypeAdapter<?>> getTypeAdapters() {
        return JsonParserHelper.getTypeAdapters();
    }

}