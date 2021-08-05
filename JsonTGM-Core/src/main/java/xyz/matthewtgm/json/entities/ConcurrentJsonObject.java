package xyz.matthewtgm.json.entities;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentJsonObject extends JsonObject {

    public ConcurrentJsonObject(JsonObject parent) {
        if (parent == null || parent.members == null)
            throw new NullPointerException("Parent object is null!");
        this.members = new ConcurrentHashMap<>();
        members.putAll(parent.members);
    }

    public ConcurrentJsonObject(Map<String, JsonElement> map) {
        if (map == null)
            throw new NullPointerException("Provided map is null!");
        this.members = new ConcurrentHashMap<>();
        members.putAll(map);
    }

    public ConcurrentJsonObject() {
        this(new ConcurrentHashMap<>());
    }

}