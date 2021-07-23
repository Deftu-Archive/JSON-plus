package xyz.matthewtgm.json.entities;

import xyz.matthewtgm.json.parser.JsonParser;

public class JsonPrimitive extends JsonElement {

    private final Object value;

    public JsonPrimitive(Object value) {
        if (value == null)
            throw new NullPointerException("Primitive value can't be null!");
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
        return JsonParser.parse(getAsString()).getAsJsonObject();
    }

    public JsonArray getAsJsonArray() {
        return JsonParser.parse(getAsString()).getAsJsonArray();
    }

    public JsonPrimitive getAsJsonPrimitive() {
        return this;
    }

    public long getAsLong() {
        return Long.parseLong(getAsString());
    }

    public int getAsInt() {
        return Integer.parseInt(getAsString());
    }

    public double getAsDouble() {
        return Double.parseDouble(getAsString());
    }

    public float getAsFloat() {
        return Float.parseFloat(getAsString());
    }

    public byte getAsByte() {
        return Byte.parseByte(getAsString());
    }

    public short getAsShort() {
        return Short.parseShort(getAsString());
    }

    public char getAsChar() {
        return getAsString().charAt(0);
    }

    public boolean getAsBoolean() {
        return Boolean.parseBoolean(getAsString());
    }

    public String getAsString() {
        return getAsString(false);
    }

    public String getAsString(boolean formatted) {
        if (formatted && value instanceof String || value instanceof Character)
            return "\"" + value + "\"";
        return toString();
    }

    public String toString() {
        return value.toString();
    }

}