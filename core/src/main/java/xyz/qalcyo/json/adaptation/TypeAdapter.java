package xyz.qalcyo.json.adaptation;

import xyz.qalcyo.json.entities.JsonElement;

public interface TypeAdapter<T> {
    JsonElement serialize(T source);
    T deserialize(JsonElement json);
}