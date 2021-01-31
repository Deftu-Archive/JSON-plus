package ga.matthewtgm.json.objects;

import ga.matthewtgm.json.base.Json;

import java.util.HashMap;

public class JsonObject extends HashMap implements Json {

    @Override
    public String toJson() {
        return null;
    }

    /**
     * Adds a variable to the JSON object
     *
     * @param key the name of the variable
     * @param value the value you're adding to this object
     * @return the object itself - QOL
     */
    public JsonObject add(Object key, Object value) {
        super.put(key, value);
        return this;
    }

}