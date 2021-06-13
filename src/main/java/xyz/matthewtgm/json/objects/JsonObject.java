package xyz.matthewtgm.json.objects;

import xyz.matthewtgm.json.base.Json;
import xyz.matthewtgm.json.parsing.JsonParser;
import xyz.matthewtgm.json.util.Utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class JsonObject<K extends String, V> extends HashMap<K, V> implements Json, ConcurrentMap<K, V> {

    public JsonObject() {}

    public JsonObject(Map<K, V> map) {
        super(map);
    }

    /**
     * the map to get the json from.
     *
     * @return the current object in a proper JSON format
     */
    public String toJson(Map<K, V> map) {
        if (map == null) return "null";
        StringBuffer sb = new StringBuffer();
        boolean first = true;
        Iterator<?> iter = map.entrySet().iterator();
        sb.append('{');
        while (iter.hasNext()) {
            if (first) first = false;
            else sb.append(',');
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
        if (k == null) sb.append("null");
        else Utils.escape(k, sb);
        sb.append('\"').append(':');
        sb.append(Utils.toJsonString(v));
        return sb.toString();
    }

    @Override
    public String toJson() {
        return toJson(this);
    }

    @Override
    public String toString() {
        return toJson();
    }

    /**
     * Adds a variable to the JSON object
     *
     * @param key   the name of the variable
     * @param value the value you're adding to this object
     * @return the object itself - QOL
     */
    public JsonObject<K, V> add(K key, V value) {
        super.put(key, value);
        return this;
    }

    public <T> T get(K key, Class<V> type) {
        if (!containsKey(key)) {
            Object newVal = null;
            if (type.isAssignableFrom(Boolean.class)) newVal = Boolean.FALSE;
            if (type.isAssignableFrom(Double.class)) newVal = 0d;
            if (type.isAssignableFrom(Float.class)) newVal = 0f;
            if (type.isAssignableFrom(Long.class)) newVal = 0L;
            if (type.isAssignableFrom(Integer.class)) newVal = 0;
            if (type.isAssignableFrom(Short.class)) newVal = 0;
            if (type.isAssignableFrom(String.class)) newVal = "";
            if (type.isAssignableFrom(Character.class)) newVal = 'C';
            if (type.isAssignableFrom(Byte.class)) newVal = 0;
            if (type.isAssignableFrom(JsonObject.class)) newVal = new JsonObject();
            if (type.isAssignableFrom(JsonArray.class)) newVal = new JsonArray();
            put(key, (V) newVal);
        }
        return (T) get(key);
    }

    public long getAsLong(String key) {
        if (!containsKey(key)) return returnDefault(Long.class, new Class[]{Long.class}, new Long[]{0L});
        return Long.parseLong(getAsString(key));
    }
    public short getAsShort(String key) {
        if (!containsKey(key)) return returnDefault(Short.class, new Class[]{Short.class}, new Short[]{0});
        return Short.parseShort(getAsString(key));
    }
    public int getAsInt(String key) {
        if (!containsKey(key)) return returnDefault(Integer.class, new Class[]{Integer.class}, new Integer[]{0});
        return Integer.parseInt(getAsString(key));
    }
    public byte getAsByte(String key) {
        if (!containsKey(key)) return returnDefault(Byte.class, new Class[]{Byte.class}, new Byte[]{0});
        return Byte.parseByte(getAsString(key));
    }
    public float getAsFloat(String key) {
        if (!containsKey(key)) return returnDefault(Float.class, new Class[]{Float.class}, new Float[]{0f});
        return Float.parseFloat(getAsString(key));
    }
    public double getAsDouble(String key) {
        if (!containsKey(key)) return returnDefault(Double.class, new Class[]{Double.class}, new Double[]{0d});
        return Double.parseDouble(getAsString(key));
    }
    public char getAsChar(String key) {
        if (!containsKey(key)) return returnDefault(Character.class, new Class[]{Character.class}, new Character[]{'a'});
        return getAsString(key).charAt(0);
    }
    public boolean getAsBoolean(String key) {
        if (!containsKey(key)) return returnDefault(Boolean.class, new Class[]{Boolean.class}, new Boolean[]{false});
        return Boolean.parseBoolean(getAsString(key));
    }
    public String getAsString(String key) {
        if (!containsKey(key)) return returnDefault(String.class, new Class[]{String.class}, new String[]{""});
        return String.valueOf(get(key));
    }
    public JsonObject<K, V> getAsJsonObject(String key) {
        if (!containsKey(key)) return returnDefault(JsonObject.class, new Class[0], new JsonObject[0]);
        return new JsonObject<>((Map<K, V>) get(key));
    }
    public JsonArray<Object> getAsJsonArray(String key) {
        if (!containsKey(key)) return returnDefault(JsonArray.class, new Class[0], new JsonArray[0]);
        return new JsonArray<>((List<Object>) get(key));
    }
    private <T> T returnDefault(Class<T> typeOfT, Class<T>[] constructorTypes, T[] constructorParams) {
        try {
            return typeOfT.getConstructor(constructorTypes).newInstance((Object) constructorParams);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}