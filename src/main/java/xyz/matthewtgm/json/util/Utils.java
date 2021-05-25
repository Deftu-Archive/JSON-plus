package xyz.matthewtgm.json.util;

import xyz.matthewtgm.json.base.Json;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Utils {

    public static String toJsonString(Object value) {
        if (value == null)
            return "null";

        if (value instanceof String)
            return "\"" + escape((String) value) + "\"";

        if (value instanceof Double) {
            if (((Double) value).isInfinite() || ((Double) value).isNaN())
                return "null";
            else
                return value.toString();
        }

        if (value instanceof Float) {
            if (((Float) value).isInfinite() || ((Float) value).isNaN())
                return "null";
            else
                return value.toString();
        }

        if (value instanceof Number)
            return value.toString();

        if (value instanceof Boolean)
            return value.toString();

        if ((value instanceof Json))
            return ((Json) value).toJson();

        if (value instanceof Map)
            return toJsonObjectString((Map) value);

        if (value instanceof List)
            return toJsonArrayString((List) value);

        return value.toString();
    }

    public static String escape(String s) {
        if (s == null)
            return null;
        StringBuffer sb = new StringBuffer();
        escape(s, sb);
        return sb.toString();
    }

    public static void escape(String s, StringBuffer sb) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                default:
                    if ((ch >= '\u0000' && ch <= '\u001F') || (ch >= '\u007F' && ch <= '\u009F') || (ch >= '\u2000' && ch <= '\u20FF')) {
                        String ss = Integer.toHexString(ch);
                        sb.append("\\u");
                        for (int k = 0; k < 4 - ss.length(); k++) {
                            sb.append('0');
                        }
                        sb.append(ss.toUpperCase());
                    } else {
                        sb.append(ch);
                    }
            }
        }
    }

    private static String toJsonObjectString(Map map) {
        if (map == null)
            return "null";

        StringBuffer sb = new StringBuffer();
        boolean first = true;
        Iterator iter = map.entrySet().iterator();

        sb.append('{');
        while (iter.hasNext()) {
            if (first)
                first = false;
            else
                sb.append(',');

            Map.Entry entry = (Map.Entry) iter.next();
            toJsonObjectString(String.valueOf(entry.getKey()), entry.getValue(), sb);
        }
        sb.append('}');
        return sb.toString();
    }

    private static String toJsonObjectString(String k, Object v, StringBuffer sb) {
        sb.append('\"');
        if(k == null)
            sb.append("null");
        else
            Utils.escape(k, sb);
        sb.append('\"').append(':');

        sb.append(toJsonString(v));

        return sb.toString();
    }

    private static String toJsonArrayString(List list) {
        if(list == null)
            return "null";

        boolean first = true;
        StringBuffer sb = new StringBuffer();
        Iterator iter=list.iterator();

        sb.append('[');
        while(iter.hasNext()){
            if(first)
                first = false;
            else
                sb.append(',');

            Object value=iter.next();
            if(value == null){
                sb.append("null");
                continue;
            }
            sb.append(toJsonString(value));
        }
        sb.append(']');
        return sb.toString();
    }

}