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

    private static GsonBuilder globalBuilder;

    private static Gson gson;
    private static Gson prettyGson;

    public static GsonBuilder getGlobalBuilder() {
        if (globalBuilder == null)
            globalBuilder = new GsonBuilder().registerTypeAdapter(JsonObject.class, new JsonObjectTypeAdapter()).registerTypeAdapter(JsonArray.class, new JsonArrayTypeAdapter()).registerTypeAdapter(UUID.class, new UuidTypeAdapter()).setLenient();
        return globalBuilder;
    }

    public static Gson getGson() {
        if (gson == null)
            gson = getGlobalBuilder().create();
        return gson;
    }

    public static Gson getPrettyGson() {
        if (prettyGson == null) prettyGson = getGlobalBuilder().setPrettyPrinting().create();
        return prettyGson;
    }

}