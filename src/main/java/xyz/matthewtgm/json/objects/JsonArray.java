package xyz.matthewtgm.json.objects;

import xyz.matthewtgm.json.base.Json;
import xyz.matthewtgm.json.util.Utils;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JsonArray<T> extends CopyOnWriteArrayList<T> implements Json {

    public String toJson(List<T> list) {
        if (list == null) return "null";
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        Iterator<?> iter = list.iterator();
        sb.append('[');
        while (iter.hasNext()) {
            if (first) first = false;
            else sb.append(',');
            Object value = iter.next();
            if (value == null) {
                sb.append("null");
                continue;
            }
            sb.append(Utils.toJsonString(value));
        }
        sb.append(']');
        return sb.toString();
    }

    @Override
    public String toJson() {
        return toJson(this);
    }

    @Override
    public String toString() {
        return toJson();
    }

    /**
     * Adds a variable to the JSON array.
     *
     * @param key the name of the variable.
     * @return the object itself - QOL.
     */
    public JsonArray<T> plus(T key) {
        add(key);
        return this;
    }

}
