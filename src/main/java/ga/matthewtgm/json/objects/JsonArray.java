package ga.matthewtgm.json.objects;

import ga.matthewtgm.json.base.Json;

import java.util.HashMap;

public class JsonArray extends HashMap implements Json {

    @Override
    public String toJson() {
        return null;
    }

    /**
     * Adds a variable to the JSON array
     *
     * @param key the name of the variable
     * @param value the value you're adding to this array
     * @return the object itself - QOL
     */
    public JsonArray add(Object key, Object value) {
        super.put(key, value);
        return this;
    }

}
