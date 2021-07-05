package xyz.matthewtgm.json.entities;

import xyz.matthewtgm.json.parser.JsonParser;
import xyz.matthewtgm.json.parser.JsonParserHelper;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

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

    public JsonObject getAsJsonObject() {
        return this;
    }

    public JsonArray getAsJsonArray() {
        throw new UnsupportedOperationException("An object can't be a JsonArray.");
    }

    public JsonPrimitive getAsJsonPrimitive() {
        throw new UnsupportedOperationException("An object can't be a JsonPrimitive.");
    }

    public long getAsLong() {
        throw new UnsupportedOperationException("An object can't be a long.");
    }

    public int getAsInt() {
        throw new UnsupportedOperationException("An object can't be an int.");
    }

    public double getAsDouble() {
        throw new UnsupportedOperationException("An object can't be a double.");
    }

    public float getAsFloat() {
        throw new UnsupportedOperationException("An object can't be a float.");
    }

    public byte getAsByte() {
        throw new UnsupportedOperationException("An object can't be a byte.");
    }

    public short getAsShort() {
        throw new UnsupportedOperationException("An object can't be a short.");
    }

    public char getAsChar() {
        throw new UnsupportedOperationException("An object can't be a char.");
    }

    public boolean getAsBoolean() {
        throw new UnsupportedOperationException("An object can't be a boolean.");
    }

    public boolean isEmpty() {
        return members.isEmpty();
    }

    public JsonObject clear() {
        members.clear();
        return this;
    }

    public JsonObject add(String key, JsonElement value) {
        members.put(key, value);
        return this;
    }

    public JsonObject add(String key, Object value) {
        JsonElement add = new JsonPrimitive(value);
        if (JsonParser.getTypeAdapters().containsKey(value.getClass())) add = JsonParserHelper.serializeTypeAdapter(JsonParser.getTypeAdapters().get(value.getClass()), value);
        System.out.println("Add 2: " + add);
        return add(key, add);
    }

    public JsonObject addIfAbsent(String key, JsonElement value) {
        members.putIfAbsent(key, new JsonPrimitive(value));
        return this;
    }

    public JsonObject addIfAbsent(String key, Object value) {
        return addIfAbsent(key, new JsonPrimitive(value));
    }

    public JsonObject addAll(Map<String, JsonElement> map) {
        members.putAll(map);
        return this;
    }

    public JsonObject addAll(JsonObject object) {
        return addAll(object.members);
    }

    public JsonObject remove(String key) {
        members.remove(key);
        return this;
    }

    public Set<Map.Entry<String, JsonElement>> entrySet() {
        return members.entrySet();
    }

    public Set<String> keySet() {
        return members.keySet();
    }

    public Collection<JsonElement> values() {
        return members.values();
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

    public JsonObject forEach(BiConsumer<? super String, ? super JsonElement> function) {
        members.forEach(function);
        return this;
    }

    public JsonElement get(String key) {
        return members.get(key);
    }

    public JsonPrimitive getPrimitive(String key) {
        return (JsonPrimitive) get(key);
    }

    public JsonArray getArray(String key) {
        return (JsonArray) get(key);
    }

    public JsonObject getObject(String key) {
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
            if (JsonParser.getTypeAdapters().containsKey(v.getClass())) v = (JsonElement) JsonParserHelper.deserializeTypeAdapter(JsonParser.getTypeAdapters().get(v.getClass()), this);
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