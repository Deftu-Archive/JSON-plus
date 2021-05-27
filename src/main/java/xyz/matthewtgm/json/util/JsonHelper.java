package xyz.matthewtgm.json.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import xyz.matthewtgm.json.base.Json;

public class JsonHelper {

    private static final Gson prettyGson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static String makePretty(Json object) {
        return prettyGson.toJson(object);
    }

}