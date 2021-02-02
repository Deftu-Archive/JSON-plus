package ga.matthewtgm.json.objects;

import ga.matthewtgm.json.base.Json;
import ga.matthewtgm.json.util.Utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonObject extends HashMap implements Json {

    /**
     * the map to get the json from.
     * @return the current object in a proper JSON format
     */
    public String toJson(Map map) {
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

            Map.Entry entry = (Map.Entry) iter.next();
            toJson(String.valueOf(entry.getKey()), entry.getValue(), sb);
        }
        sb.append('}');
        return sb.toString();
    }

    /**
     * @param k key.
     * @param v value.
     * @param sb the string builder to append to.
     * @return the current object in a proper JSON format
     */
    public String toJson(String k, Object v, StringBuffer sb) {
        sb.append('\"');
        if(k == null)
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
    public JsonObject add(Object key, Object value) {
        super.put(key, value);
        return this;
    }

}