package xyz.matthewtgm.json.util;

public class JsonHelper {

    /*public static String makePretty(Json object) {
        return GlobalGson.getPrettyGson().toJson(object);
    }

    public static String makePretty(String jsonStr, Class<? extends Json> jsonType) {
        return GlobalGson.getPrettyGson().toJson(JsonParser.parse(jsonStr, jsonType));
    }

    public static boolean isValidJson(Object o) {
        String str = o.toString();
        boolean valid = false;

        try {
            JsonObject<String, Object> object = JsonParser.parseObj(str);
            if (object != null) valid = true;
        } catch (Exception ignored) {}

        try {
            JsonArray<?> array = JsonParser.parseArr(str);
            if (array != null) valid = true;
        } catch (Exception ignored) {}

        return valid;
    }

    public static <T extends Json> T getJsonType(String json) {
        if (!isValidJson(json)) return null;
        T value = null;

        try {
            value = (T) JsonParser.parseObj(json);
        } catch (Exception ignored) {}

        try {
            value = (T) JsonParser.parseArr(json);
        } catch (Exception ignored) {}

        return value;
    }*/

}