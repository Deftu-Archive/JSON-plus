package xyz.deftu.json.adaptation;

import xyz.deftu.json.entities.JsonElement;

public interface TypeAdapter<T> {
    JsonElement serialize(T source);
    T deserialize(JsonElement json);
}