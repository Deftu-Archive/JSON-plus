package xyz.matthewtgm.json.base;

public interface Json {
    /**
     * @return the current object in a proper JSON format
     */
    String toJson();
    /**
     * Creates a deep-copy of the json.
     */
    Json cloneJson();
}