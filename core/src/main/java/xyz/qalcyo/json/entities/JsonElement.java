package xyz.qalcyo.json.entities;

/**
 * Base class for all JSON elements.
 */
public abstract class JsonElement {

    /**
     * @return A copy of the element with all it's values.
     * @author MatthewTGM
     * @since 2.1
     */
    public abstract JsonElement copy();

    /**
     * @return The element as a JsonObject.
     * @author MatthewTGM
     * @since 2.1
     */
    public abstract JsonObject getAsJsonObject();
    /**
     * @return The element as a JsonArray.
     * @author MatthewTGM
     * @since 2.1
     */
    public abstract JsonArray getAsJsonArray();
    /**
     * @return The element as a JsonPrimitive.
     * @author MatthewTGM
     * @since 2.1
     */
    public abstract JsonPrimitive getAsJsonPrimitive();
    /**
     * @return The element as a long.
     * @author MatthewTGM
     * @since 2.1
     */
    public abstract long getAsLong();
    /**
     * @return The element as an int.
     * @author MatthewTGM
     * @since 2.1
     */
    public abstract int getAsInt();
    /**
     * @return The element as a double.
     * @author MatthewTGM
     * @since 2.1
     */
    public abstract double getAsDouble();
    /**
     * @return The element as a float.
     * @author MatthewTGM
     * @since 2.1
     */
    public abstract float getAsFloat();
    /**
     * @return The element as a byte.
     * @author MatthewTGM
     * @since 2.1
     */
    public abstract byte getAsByte();
    /**
     * @return The element as a short.
     * @author MatthewTGM
     * @since 2.1
     */
    public abstract short getAsShort();
    /**
     * @return The element as a char.
     * @author MatthewTGM
     * @since 2.1
     */
    public abstract char getAsChar();
    /**
     * @return The element as a boolean.
     * @author MatthewTGM
     * @since 2.1
     */
    public abstract boolean getAsBoolean();
    /**
     * @return The element as a String.
     * @author MatthewTGM
     * @since 2.1
     */
    public abstract String getAsString();
    /**
     * @return The element as a "formatted" String.
     * @author MatthewTGM
     * @since 2.6.2
     */
    public abstract String getAsString(boolean formatted);
    /**
     * @return Whether this element is concurrent or not.
     * @author MatthewTGM
     * @since 2.9.0
     */
    public boolean isConcurrent() {
        return this instanceof ConcurrentJsonObject || this instanceof CopyOnWriteJsonArray;
    }
    /**
     * @return Whether the element is a JsonObject or not
     * @author MatthewTGM
     * @since 2.4
     */
    public boolean isJsonObject() {
        return this instanceof JsonObject;
    }
    /**
     * @return Whether the element is a JsonArray or not
     * @author MatthewTGM
     * @since 2.4
     */
    public boolean isJsonArray() {
        return this instanceof JsonArray;
    }
    /**
     * @return Whether the element is a JsonPrimitive or not
     * @author MatthewTGM
     * @since 2.4
     */
    public boolean isJsonPrimitive() {
        return this instanceof JsonPrimitive;
    }
    /**
     * @return Whether the element is a JsonPrimitive or not
     * @author MatthewTGM
     * @since 2.6
     */
    public boolean isLong() {
        return isJsonPrimitive() && (getAsJsonPrimitive()).getValue() instanceof Long;
    }
    /**
     * @return Whether the element is a int or not
     * @author MatthewTGM
     * @since 2.6
     */
    public boolean isInt() {
        return isJsonPrimitive() && (getAsJsonPrimitive()).getValue() instanceof Integer;
    }
    /**
     * @return Whether the element is a double or not
     * @author MatthewTGM
     * @since 2.6
     */
    public boolean isDouble() {
        return isJsonPrimitive() && (getAsJsonPrimitive()).getValue() instanceof Double;
    }
    /**
     * @return Whether the element is a float or not
     * @author MatthewTGM
     * @since 2.6
     */
    public boolean isFloat() {
        return isJsonPrimitive() && (getAsJsonPrimitive()).getValue() instanceof Float;
    }
    /**
     * @return Whether the element is a byte or not
     * @author MatthewTGM
     * @since 2.6
     */
    public boolean isByte() {
        return isJsonPrimitive() && (getAsJsonPrimitive()).getValue() instanceof Byte;
    }
    /**
     * @return Whether the element is a short or not
     * @author MatthewTGM
     * @since 2.6
     */
    public boolean isShort() {
        return isJsonPrimitive() && (getAsJsonPrimitive()).getValue() instanceof Short;
    }
    /**
     * @return Whether the element is a char or not
     * @author MatthewTGM
     * @since 2.6
     */
    public boolean isChar() {
        return isJsonPrimitive() && (getAsJsonPrimitive()).getValue() instanceof Character;
    }
    /**
     * @return Whether the element is a boolean or not
     * @author MatthewTGM
     * @since 2.6
     */
    public boolean isBoolean() {
        return isJsonPrimitive() && (getAsJsonPrimitive()).getValue() instanceof Boolean;
    }
    /**
     * @return Whether the element is a String or not
     * @author MatthewTGM
     * @since 2.6
     */
    public boolean isString() {
        return isJsonPrimitive() && (getAsJsonPrimitive()).getValue() instanceof String;
    }

    /**
     * @return A String representation of the element.
     * @author MatthewTGM
     * @since 2.1
     */
    public String toString() {
        return getAsString();
    }

}