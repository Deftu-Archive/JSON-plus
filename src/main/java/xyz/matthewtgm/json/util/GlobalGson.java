package xyz.matthewtgm.json.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import xyz.matthewtgm.json.gson.adapters.JsonArrayTypeAdapter;
import xyz.matthewtgm.json.gson.adapters.JsonObjectTypeAdapter;
import xyz.matthewtgm.json.gson.adapters.UuidTypeAdapter;
import xyz.matthewtgm.json.objects.JsonArray;
import xyz.matthewtgm.json.objects.JsonObject;

import java.lang.reflect.Type;
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
            resetGson();
        return gson;
    }

    public static Gson getPrettyGson() {
        if (prettyGson == null)
            resetPrettyGson();
        return prettyGson;
    }

    public static void registerTypeAdapters(TypeAdapterHolder... adapters) {
        for (TypeAdapterHolder adapter : adapters) getGlobalBuilder().registerTypeAdapter(adapter.type, adapter.adapter);
        resetGson();
        resetPrettyGson();
    }

    public static void resetGson() {
        gson = getGlobalBuilder().create();
    }

    public static void resetPrettyGson() {
        prettyGson = getGlobalBuilder().setPrettyPrinting().create();
    }

    public static class TypeAdapterHolder {
        public final Type type;
        public final Object adapter;
        public TypeAdapterHolder(Type type, Object adapter) {
            this.type = type;
            this.adapter = adapter;
        }
    }

}