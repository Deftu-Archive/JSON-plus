package xyz.matthewtgm.json.entities;

import xyz.matthewtgm.json.parser.JsonParserHelper;

public class JsonPrimitive extends JsonElement {

    private final Object value;

    public JsonPrimitive(JsonPrimitive parent) {
        if (parent == null || parent.value == null) throw new NullPointerException("Primitive value can't be null!");
        this.value = parent.value;
    }

    public JsonPrimitive(Object value) {
        if (value == null) throw new NullPointerException("Primitive value can't be null!");
        this.value = value;
    }

    /**
     * @return The same value. (primitives are immutable.)
     */
    public JsonPrimitive copy() {
        return this;
    }

    public String toString() {
        return JsonParserHelper.parsePrimitive(value);
    }

}