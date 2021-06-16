package xyz.matthewtgm.json.util;

import xyz.matthewtgm.json.base.Json;

public class JsonHelper {

    public static String makePretty(Json object) {
        return GlobalGson.getPrettyGson().toJson(object);
    }

}