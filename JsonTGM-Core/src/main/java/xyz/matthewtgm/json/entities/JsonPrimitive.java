package xyz.matthewtgm.json.entities;

import xyz.matthewtgm.json.parser.JsonParser;

public class JsonPrimitive extends JsonElement {

    private final Object value;

    public JsonPrimitive(final Object value) {
        if (value == null) throw new NullPointerException("Primitive value can't be null!");
        this.value = value;
    }

    public JsonPrimitive(JsonPrimitive parent) {
        this(parent == null ? null : parent.value);
    }

    public Object getValue() {
        return value;
    }

    /**
     * @return The same value. (primitives are immutable.)
     */
    public JsonPrimitive copy() {
        return this;
    }

    public JsonObject getAsJsonObject() {
        return (JsonObject) JsonParser.parse(toString());
    }

    public JsonArray getAsJsonArray() {
        return (JsonArray) JsonParser.parse(toString());
    }

    public JsonPrimitive getAsJsonPrimitive() {
        return this;
    }

    public long getAsLong() {
        return Long.parseLong(toString());
    }

    public int getAsInt() {
        return Integer.parseInt(toString());
    }

    public double getAsDouble() {
        return Double.parseDouble(toString());
    }

    public float getAsFloat() {
        return Float.parseFloat(toString());
    }

    public byte getAsByte() {
        return Byte.parseByte(toString());
    }

    public short getAsShort() {
        return Short.parseShort(toString());
    }

    public char getAsChar() {
        return toString().charAt(0);
    }

    public boolean getAsBoolean() {
        return Boolean.parseBoolean(toString());
    }

    public String getAsString() {
        if (value instanceof String || value instanceof Character) return "\"" + value + "\"";
        return value.toString();
    }

    public String toString() {
        return value.toString();
    }

}