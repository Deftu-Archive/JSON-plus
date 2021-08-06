package xyz.matthewtgm.json.entities;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentJsonObject extends JsonObject {

    public ConcurrentJsonObject(JsonObject parent) {
        if (parent == null || parent.members == null)
            throw new NullPointerException("Parent object is null!");
        this.members = Collections.synchronizedMap(new LinkedHashMap<>());
        members.putAll(parent.members);
    }

    public ConcurrentJsonObject(Map<String, JsonElement> map) {
        if (map == null)
            throw new NullPointerException("Provided map is null!");
        this.members = Collections.synchronizedMap(new LinkedHashMap<>());
        members.putAll(map);
    }

    public ConcurrentJsonObject() {
        this(Collections.synchronizedMap(new LinkedHashMap<>()));
    }

}