package xyz.matthewtgm.json.entities;

import xyz.matthewtgm.json.parser.JsonParser;
import xyz.matthewtgm.json.parser.JsonParserHelper;

import java.util.*;
import java.util.function.Predicate;

public class JsonArray extends JsonElement implements Iterable<JsonElement> {

    protected List<JsonElement> elements = new ArrayList<>();

    public JsonArray(JsonArray parent) {
        if (parent == null || parent.elements == null) throw new NullPointerException("Parent is null!");
        elements.addAll(parent.elements);
    }

    public JsonArray(List<JsonElement> list) {
        elements.addAll(list);
    }

    public JsonArray() {
        this(new ArrayList<>());
    }

    public JsonArray copy() {
        return new JsonArray(this);
    }

    public JsonObject getAsJsonObject() {
        throw new UnsupportedOperationException("An array can't be a JsonObject.");
    }

    public JsonArray getAsJsonArray() {
        return this;
    }

    public JsonPrimitive getAsJsonPrimitive() {
        throw new UnsupportedOperationException("An array can't be a JsonPrimitive.");
    }

    public long getAsLong() {
        throw new UnsupportedOperationException("An array can't be a long.");
    }

    public int getAsInt() {
        throw new UnsupportedOperationException("An array can't be an int.");
    }

    public double getAsDouble() {
        throw new UnsupportedOperationException("An array can't be a double.");
    }

    public float getAsFloat() {
        throw new UnsupportedOperationException("An array can't be a float.");
    }

    public byte getAsByte() {
        throw new UnsupportedOperationException("An array can't be a byte.");
    }

    public short getAsShort() {
        throw new UnsupportedOperationException("An array can't be a short.");
    }

    public char getAsChar() {
        throw new UnsupportedOperationException("An array can't be a char.");
    }

    public boolean getAsBoolean() {
        throw new UnsupportedOperationException("An array can't be a boolean.");
    }

    public JsonArray clear() {
        elements.clear();
        return this;
    }

    public JsonElement[] toArray() {
        return elements.toArray(new JsonElement[0]);
    }

    public JsonArray add(Object element) {
        elements.add(new JsonPrimitive(element));
        return this;
    }

    public JsonArray addAll(Object... elements) {
        for (Object element : elements) add(element);
        return this;
    }

    public JsonArray set(int index, Object element) {
        elements.set(index, new JsonPrimitive(element));
        return this;
    }

    public JsonArray remove(Object element) {
        elements.remove(new JsonPrimitive(element));
        return this;
    }

    public JsonArray add(JsonElement element) {
        elements.add(element);
        return this;
    }

    public JsonArray addAll(JsonElement... elements) {
        for (JsonElement element : elements) add(element);
        return this;
    }

    public JsonArray addAll(JsonArray array) {
        return addAll(array.toArray());
    }

    public JsonArray set(int index, JsonElement element) {
        elements.set(index, element);
        return this;
    }

    public JsonArray remove(JsonElement element) {
        elements.remove(element);
        return this;
    }

    public JsonArray remove(int index) {
        elements.remove(index);
        return this;
    }

    public JsonArray removeIf(Predicate<? super JsonElement> filter) {
        elements.removeIf(filter);
        return this;
    }

    public JsonArray sort(Comparator<? super JsonElement> comparator) {
        elements.sort(comparator);
        return this;
    }

    @Deprecated
    public boolean contains(JsonElement element) {
        return elements.contains(element);
    }

    public boolean has(JsonElement element) {
        return elements.contains(element);
    }

    public boolean has(Object o) {
        o = Objects.requireNonNull(o);
        for (JsonElement element : this) {
            if (!element.isJsonPrimitive())
                continue;
            Object value = element.getAsJsonPrimitive().getValue();
            if (o.equals(value) || o.toString().equals(value.toString()))
                return true;
        }
        return false;
    }

    public int size() {
        return elements.size();
    }

    public Iterator<JsonElement> iterator() {
        return elements.iterator();
    }

    public JsonElement get(int index) {
        return elements.get(index);
    }

    public long getAsLong(int index) {
        return get(index).getAsLong();
    }

    public int getAsInt(int index) {
        return get(index).getAsInt();
    }

    public double getAsDouble(int index) {
        return get(index).getAsDouble();
    }

    public float getAsFloat(int index) {
        return get(index).getAsFloat();
    }

    public byte getAsByte(int index) {
        return get(index).getAsByte();
    }

    public short getAsShort(int index) {
        return get(index).getAsShort();
    }

    public char getAsChar(int index) {
        return get(index).getAsChar();
    }

    public boolean getAsBoolean(int index) {
        return get(index).getAsBoolean();
    }

    public String getAsString(int index) {
        return get(index).getAsString();
    }

    public JsonPrimitive getAsJsonPrimitive(int index) {
        return get(index).getAsJsonPrimitive();
    }

    public JsonArray getAsJsonArray(int index) {
        return get(index).getAsJsonArray();
    }

    public JsonObject getAsJsonObject(int index) {
        return get(index).getAsJsonObject();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public int indexOf(JsonElement element) {
        return elements.indexOf(element);
    }

    public String getAsString() {
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        Iterator<JsonElement> iterator = iterator();

        sb.append('[');
        while (iterator.hasNext()) {
            if (first)
                first = false;
            else sb.append(',');
            JsonElement value = iterator.next();
            if (value == null) {
                sb.append("null");
                continue;
            }
            if (JsonParser.getTypeAdapters().containsKey(value.getClass()))
                value = JsonParserHelper.serializeTypeAdapter(JsonParser.getTypeAdapters().get(value.getClass()), value);
            sb.append(value.getAsString(true));
        }
        sb.append(']');
        return sb.toString();
    }

    public String getAsString(boolean formatted) {
        return getAsString();
    }

    public boolean equals(Object o) {
        return (o == this) || (o instanceof JsonArray && ((JsonArray) o).elements.equals(elements));
    }

    public int hashCode() {
        return elements.hashCode();
    }

}