package xyz.matthewtgm.json.gson.adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TypeAdapterGlobals {

    public static final Gson gson = new GsonBuilder()
            .setLenient()
            .create();

}