package xyz.matthewtgm.json.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import xyz.matthewtgm.json.gson.adapters.JsonArrayTypeAdapter;
import xyz.matthewtgm.json.gson.adapters.JsonObjectTypeAdapter;
import xyz.matthewtgm.json.gson.adapters.UuidTypeAdapter;
import xyz.matthewtgm.json.objects.JsonArray;
import xyz.matthewtgm.json.objects.JsonObject;

import java.util.UUID;

public class GlobalGson {

    private static final GsonBuilder globalBuilder = new GsonBuilder()
            .registerTypeAdapter(JsonObject.class, new JsonObjectTypeAdapter())
            .registerTypeAdapter(JsonArray.class, new JsonArrayTypeAdapter())
            .registerTypeAdapter(UUID.class, new UuidTypeAdapter())

            .setLenient();

    private static final Gson gson = globalBuilder
            .create();
    private static final Gson prettyGson = globalBuilder
            .setPrettyPrinting()
            .create();

    public static GsonBuilder getGlobalBuilder() {
        return globalBuilder;
    }

    public static Gson getGson() {
        return gson;
    }

    public static Gson getPrettyGson() {
        return prettyGson;
    }

}