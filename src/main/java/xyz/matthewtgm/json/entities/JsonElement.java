package xyz.matthewtgm.json.entities;

/**
 * Base class for all JSON elements.
 */
public abstract class JsonElement {

    /**
     * @return A copy of the element with all it's values.
     */
    public abstract JsonElement copy();

    /**
     * @return The element as a JsonObject.
     */
    public abstract JsonObject getAsJsonObject();
    /**
     * @return The element as a JsonArray.
     */
    public abstract JsonArray getAsJsonArray();
    /**
     * @return The element as a JsonPrimitive.
     */
    public abstract JsonPrimitive getAsJsonPrimitive();
    /**
     * @return The element as a long.
     */
    public abstract long getAsLong();
    /**
     * @return The element as an int.
     */
    public abstract int getAsInt();
    /**
     * @return The element as a double.
     */
    public abstract double getAsDouble();
    /**
     * @return The element as a float.
     */
    public abstract float getAsFloat();
    /**
     * @return The element as a byte.
     */
    public abstract byte getAsByte();
    /**
     * @return The element as a short.
     */
    public abstract short getAsShort();
    /**
     * @return The element as a char.
     */
    public abstract char getAsChar();
    /**
     * @return The element as a boolean.
     */
    public abstract boolean getAsBoolean();
    /**
     * @return The element as a String.
     */
    public String getAsString() {
        return toString();
    }

    /**
     * @return A String representation of the element.
     */
    public abstract String toString();

}