package xyz.matthewtgm.json.entities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class JsonObject extends JsonElement {

    /* Must be concurrent in-case of concurrency. (obviously.) */
    private final Map<String, JsonElement> members = new ConcurrentHashMap<>();

    public JsonObject(JsonObject parent) {
        if (parent == null || parent.members == null) throw new NullPointerException("Parent object is null!");
        members.putAll(parent.members);
    }

    public JsonObject(Map<String, JsonElement> map) {
        if (map == null) throw new NullPointerException("Provided map is null!");
        members.putAll(map);
    }

    public JsonObject() {
        this(new HashMap<>());
    }

    public JsonObject copy() {
        return new JsonObject(this);
    }

    public JsonObject add(String key, JsonElement value) {
        members.put(key, value);
        return this;
    }

    public JsonObject remove(String key) {
        members.remove(key);
        return this;
    }

    public JsonObject add(String key, Object value) {
        members.put(key, new JsonPrimitive(value));
        return this;
    }

    public Set<Map.Entry<String, JsonElement>> entrySet() {
        return members.entrySet();
    }

    public Set<String> keySet() {
        return members.keySet();
    }

    public int size() {
        return members.size();
    }

    public boolean hasKey(String key) {
        return members.containsKey(key);
    }

    public boolean hasValue(JsonElement value) {
        return members.containsValue(value);
    }

    public boolean has(Object o) {
        return hasKey(o.toString()) || hasValue(new JsonPrimitive(o));
    }

    public JsonElement get(String key) {
        return members.get(key);
    }

    public JsonPrimitive getAsPrimitive(String key) {
        return (JsonPrimitive) get(key);
    }

    public JsonArray getAsArray(String key) {
        return (JsonArray) get(key);
    }

    public JsonObject getAsObject(String key) {
        return (JsonObject) get(key);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        Iterator<?> iter = ((Map<?, ?>) this.members).entrySet().iterator();

        sb.append('{');
        while (iter.hasNext()) {
            if (first) first = false;
            else sb.append(',');
            Map.Entry<?, ?> entry = (Map.Entry<?, ?>) iter.next();

            String k = String.valueOf(entry.getKey());
            JsonElement v = (JsonElement) entry.getValue();
            sb.append('\"');
            if(k == null) sb.append("null");
            sb.append(k);
            sb.append('\"');
            sb.append(':');
            sb.append(v);
        }
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object o) {
        return (o == this) || (o instanceof JsonObject && ((JsonObject) o).members.equals(members));
    }

    public int hashCode() {
        return members.hashCode();
    }

}