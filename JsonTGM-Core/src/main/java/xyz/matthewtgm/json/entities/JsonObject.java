package xyz.matthewtgm.json.entities;

import xyz.matthewtgm.json.parser.JsonParser;
import xyz.matthewtgm.json.parser.JsonParserHelper;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class JsonObject extends JsonElement {

    protected Map<String, JsonElement> members = new LinkedHashMap<>();

    public JsonObject(JsonObject parent) {
        if (parent == null || parent.members == null)
            throw new NullPointerException("Parent object is null!");
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
        if (JsonParser.getTypeAdapters().containsKey(value.getClass()))
            add = JsonParserHelper.serializeTypeAdapter(JsonParser.getTypeAdapters().get(value.getClass()), value);
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

    public JsonObject merge(JsonObject object, boolean maintainCurrent) {
        for (Map.Entry<String, JsonElement> entry : object.members.entrySet())
            if (members.containsKey(entry.getKey()) && !maintainCurrent)
                members.put(entry.getKey(), entry.getValue());
        return this;
    }

    public JsonObject forEach(BiConsumer<? super String, ? super JsonElement> function) {
        members.forEach(function);
        return this;
    }

    public JsonObject compute(String key, BiFunction<? super String, ? super JsonElement, ? extends JsonElement> function) {
        members.compute(key, function);
        return this;
    }

    public JsonObject computeIfAbsent(String key, Function<? super String, ? extends JsonElement> function) {
        members.computeIfAbsent(key, function);
        return this;
    }

    public JsonElement get(String key) {
        return members.get(key);
    }

    public JsonElement getOrDefault(String key, JsonElement defaultValue) {
        return hasKey(key) ? get(key) : defaultValue;
    }

    public JsonElement getOrDefault(String key, Object defaultValue) {
        return getOrDefault(key, JsonParser.parse(Objects.requireNonNull(defaultValue).toString()));
    }

    public long getAsLong(String key) {
        return get(key).getAsLong();
    }

    public int getAsInt(String key) {
        return get(key).getAsInt();
    }

    public double getAsDouble(String key) {
        return get(key).getAsDouble();
    }

    public float getAsFloat(String key) {
         return get(key).getAsFloat();
    }

    public byte getAsByte(String key) {
        return get(key).getAsByte();
    }

    public short getAsShort(String key) {
        return get(key).getAsShort();
    }

    public char getAsChar(String key) {
        return get(key).getAsChar();
    }

    public boolean getAsBoolean(String key) {
        return get(key).getAsBoolean();
    }

    public String getAsString(String key) {
        return get(key).getAsString();
    }

    public JsonPrimitive getAsPrimitive(String key) {
        return get(key).getAsJsonPrimitive();
    }

    public JsonArray getAsArray(String key) {
        return get(key).getAsJsonArray();
    }

    public JsonObject getAsObject(String key) {
        return get(key).getAsJsonObject();
    }

    @Deprecated
    public JsonPrimitive getPrimitive(String key) {
        return get(key).getAsJsonPrimitive();
    }

    @Deprecated
    public JsonArray getArray(String key) {
        return get(key).getAsJsonArray();
    }

    @Deprecated
    public JsonObject getObject(String key) {
        return get(key).getAsJsonObject();
    }

    public String getAsString() {
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
            if(k == null)
                sb.append("null");
            sb.append(k);
            sb.append('\"');
            sb.append(':');
            if (JsonParser.getTypeAdapters().containsKey(v.getClass()))
                v = JsonParserHelper.serializeTypeAdapter(JsonParser.getTypeAdapters().get(v.getClass()), this);
            sb.append(v.getAsString(true));
        }
        sb.append('}');
        return sb.toString();
    }

    public String getAsString(boolean formatted) {
        return getAsString();
    }

    public boolean equals(Object o) {
        return (o == this) || (o instanceof JsonObject && ((JsonObject) o).members.equals(members));
    }

    public int hashCode() {
        return members.hashCode();
    }

}