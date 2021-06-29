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
     * @return A String representation of the element.
     */
    public abstract String toString();

}