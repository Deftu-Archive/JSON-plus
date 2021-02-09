package ga.matthewtgm.json.objects;

import ga.matthewtgm.json.base.Json;
import ga.matthewtgm.json.util.Utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonObject extends HashMap<String, Object> implements Json {

    /**
     * the map to get the json from.
     *
     * @return the current object in a proper JSON format
     */
    public String toJson(Map<?, ?> map) {
        if (map == null)
            return "null";

        StringBuffer sb = new StringBuffer();
        boolean first = true;
        Iterator iter = map.entrySet().iterator();

        sb.append('{');
        while (iter.hasNext()) {
            if (first)
                first = false;
            else
                sb.append(',');

            Map.Entry<?, ?> entry = (Map.Entry<?, ?>) iter.next();
            toJson(String.valueOf(entry.getKey()), entry.getValue(), sb);
        }
        sb.append('}');
        return sb.toString();
    }

    /**
     * @param k  key.
     * @param v  value.
     * @param sb the string builder to append to.
     * @return the current object in a proper JSON format
     */
    public String toJson(String k, Object v, StringBuffer sb) {
        sb.append('\"');
        if (k == null)
            sb.append("null");
        else
            Utils.escape(k, sb);
        sb.append('\"').append(':');

        sb.append(Utils.toJsonString(v));

        return sb.toString();
    }

    @Override
    public String toJson() {
        return this.toJson(this);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

    /**
     * Adds a variable to the JSON object
     *
     * @param key   the name of the variable
     * @param value the value you're adding to this object
     * @return the object itself - QOL
     */
    public JsonObject add(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * Returns an inner-JsonObject
     *
     * @param key the name of the object
     * @return {@code this}
     */
    public JsonObject get(String key) {
        if(!(super.get(key) instanceof JsonObject)) throw new IllegalStateException("Expected JsonObject.");
        if(super.get(key) == null) throw new NullPointerException("Object cannot be null.");
        return (JsonObject) super.get(key);
    }

}