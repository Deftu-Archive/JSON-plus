package ga.matthewtgm.json.objects;

import ga.matthewtgm.json.base.Json;
import ga.matthewtgm.json.util.Utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class JsonObject extends HashMap<String, Object> implements Json, ConcurrentMap<String, Object> {

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

    public <T> T get(String key, Class<?> type) {
        if (!this.containsKey(key)) {
            Object newVal = new Object();
            if (type.isAssignableFrom(Boolean.class)) newVal = Boolean.FALSE;
            if (type.isAssignableFrom(Double.class)) newVal = 0D;
            if (type.isAssignableFrom(Float.class)) newVal = 0F;
            if (type.isAssignableFrom(Long.class)) newVal = 0L;
            if (type.isAssignableFrom(Integer.class)) newVal = 0;
            if (type.isAssignableFrom(Short.class)) newVal = 0;
            if (type.isAssignableFrom(Character.class)) newVal = 'A';
            if (type.isAssignableFrom(Byte.class)) newVal = 0;
            if (type.isAssignableFrom(JsonObject.class)) newVal = new JsonObject();
            if (type.isAssignableFrom(JsonArray.class)) newVal = new JsonArray();
            this.put(key, newVal);
        }
        return (T) this.get(key);
    }

    public short getValueAsShort(String key) {
        return (short) get(key);
    }
    public int getValueAsInt(String key) {
        return (int) get(key);
    }
    public byte getValueAsByte(String key) {
        return (byte) get(key);
    }
    public float getValueAsFloat(String key) {
        return (float) get(key);
    }
    public double getValueAsDouble(String key) {
        return (double) get(key);
    }
    public char getValueAsChar(String key) {
        return (char) get(key);
    }
    public boolean getValueAsBoolean(String key) {
        return (boolean) get(key);
    }
    public String getValueAsString(String key) {
        return (String) get(key);
    }
    public JsonObject getValueAsJsonObject(String key) {
        return (JsonObject) get(key);
    }
    public JsonArray getValueAsJsonArray(String key) {
        return (JsonArray) get(key);
    }

}