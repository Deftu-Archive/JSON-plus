package xyz.queffe.json.adaptation;

import xyz.queffe.json.entities.JsonElement;

public interface TypeAdapter<T> {
    JsonElement serialize(T source);
    T deserialize(JsonElement json);
}