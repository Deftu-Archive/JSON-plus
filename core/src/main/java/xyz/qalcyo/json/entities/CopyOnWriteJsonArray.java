package xyz.qalcyo.json.entities;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteJsonArray extends JsonArray {

    public CopyOnWriteJsonArray(JsonArray parent) {
        if (parent == null || parent.elements == null)
            throw new NullPointerException("Parent is null!");
        this.elements = new CopyOnWriteArrayList<>();
        elements.addAll(parent.elements);
    }

    public CopyOnWriteJsonArray(List<JsonElement> list) {
        this.elements = new CopyOnWriteArrayList<>();
        elements.addAll(list);
    }

    public CopyOnWriteJsonArray() {
        this(new CopyOnWriteArrayList<>());
    }

}