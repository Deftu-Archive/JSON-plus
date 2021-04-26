package ga.matthewtgm.json.objects;

import ga.matthewtgm.json.base.Json;
import ga.matthewtgm.json.util.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JsonArray extends CopyOnWriteArrayList<Object> implements Json {

    public String toJson(List<Object> list) {
        if (list == null)
            return "null";

        boolean first = true;
        StringBuffer sb = new StringBuffer();
        Iterator<Object> iter = list.iterator();

        sb.append('[');
        while (iter.hasNext()) {
            if (first)
                first = false;
            else
                sb.append(',');

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
        return this.toJson(this);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

    /**
     * Adds a variable to the JSON array.
     *
     * @param key the name of the variable.
     * @return the object itself - QOL.
     */
    public JsonArray plus(Object key) {
        super.add(key);
        return this;
    }

}
