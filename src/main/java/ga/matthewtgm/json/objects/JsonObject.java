package ga.matthewtgm.json.objects;


import ga.matthewtgm.json.base.Json;

import java.util.HashMap;

public class JsonObject extends HashMap implements Json {

    @Override
    public String toJson() {
        return null;
    }

    public JsonObject put(Object key, Object value) {
        super.put(key, value);
        return this;
    }

}