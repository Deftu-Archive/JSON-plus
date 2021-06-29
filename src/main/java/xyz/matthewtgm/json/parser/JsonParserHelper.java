package xyz.matthewtgm.json.parser;

import xyz.matthewtgm.json.entities.JsonArray;
import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.entities.JsonPrimitive;
import xyz.matthewtgm.json.exceptions.JsonParseException;
import xyz.matthewtgm.json.util.Utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonParserHelper {

    static void throwParseException(Object o) {
        try {
            if (o == null) throw new JsonParseException();
            if (o instanceof Throwable) throw new JsonParseException((Throwable) o);
            if (o instanceof String) throw new JsonParseException(o.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static JsonObject parseObject(String input) {
        return null;
    }

    static JsonArray parseArray(String input) {
        return null;
    }

    static String escape(String s) {
        if (s == null)
            return null;
        StringBuffer sb = new StringBuffer();
        escape(s, sb);
        return sb.toString();
    }

    static void escape(String s, StringBuffer sb) {
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

    static Object parseDecimalNumber(Object val) {
        try {
            Float floa = (Float) val;
            if (floa.isInfinite() || floa.isNaN()) return "null";
            else return floa.toString();
        } catch (Exception ignored) {}

        try {
            Double doub = (Double) val;
            if (doub.isInfinite() || doub.isNaN()) return "null";
            else return doub.toString();
        } catch (Exception ignored) {}
        return "null";
    }

    static String createObjectString(Object val) {
        Map<?, ?> map = (Map<?, ?>) val;
        StringBuffer sb = new StringBuffer();
        boolean first = true;
        Iterator<?> iter = map.entrySet().iterator();
        sb.append('{');
        while (iter.hasNext()) {
            if (first) first = false;
            else sb.append(',');
            Map.Entry<?, ?> entry = (Map.Entry<?, ?>) iter.next();

            String k = String.valueOf(entry.getKey());
            JsonPrimitive v = (JsonPrimitive) entry.getValue();
            sb.append('\"');
            if(k == null) sb.append("null");
            else Utils.escape(k, sb);
            sb.append('\"').append(':');
            sb.append(JsonParser.parseJsonPrimitive(v));
        }
        sb.append('}');
        return sb.toString();
    }

    static String createArrayString(Object val) {
        List<?> list = (List<?>) val;
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
            sb.append(JsonParser.parseJsonPrimitive(value));
        }
        sb.append(']');
        return sb.toString();
    }

}