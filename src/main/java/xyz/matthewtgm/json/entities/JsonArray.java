package xyz.matthewtgm.json.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonArray extends JsonElement {

    private final List<JsonElement> elements = new ArrayList<>();

    public JsonArray(JsonArray parent) {
        if (parent == null || parent.elements == null) throw new NullPointerException("Parent is null!");
        elements.addAll(parent.elements);
    }

    public JsonArray(List<JsonElement> list) {
        elements.addAll(list);
    }

    public JsonArray() {
        this(new ArrayList<>());
    }

    public JsonArray copy() {
        return new JsonArray(this);
    }

    public JsonArray add(JsonElement element) {
        elements.add(element);
        return this;
    }

    public JsonArray addAll(JsonElement... elements) {
        for (JsonElement element : elements) add(element);
        return this;
    }

    public JsonArray addAll(JsonArray array) {
        return addAll(array.elements.toArray(new JsonElement[Integer.MAX_VALUE]));
    }

    public JsonArray set(int index, JsonElement element) {
        elements.set(index, element);
        return this;
    }

    public JsonArray remove(JsonElement element) {
        elements.remove(element);
        return this;
    }

    public JsonArray remove(int index) {
        elements.remove(index);
        return this;
    }

    public boolean contains(JsonElement element) {
        return elements.contains(element);
    }

    public int size() {
        return elements.size();
    }

    public Iterator<JsonElement> iterator() {
        return elements.iterator();
    }

    public JsonElement get(int index) {
        return elements.get(index);
    }

    public String toString() {
        return elements.toString();
    }

    public boolean equals(Object o) {
        return (o == this) || (o instanceof JsonArray && ((JsonArray) o).elements.equals(elements));
    }

    public int hashCode() {
        return elements.hashCode();
    }

}