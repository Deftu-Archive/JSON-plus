package xyz.matthewtgm.json.entities;

import xyz.matthewtgm.json.parser.JsonParserHelper;

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
    public String getAsString() {
        return toString();
    }

    /**
     * @return A String representation of the element.
     * @author MatthewTGM
     * @since 2.1
     */
    public abstract String toString();

}