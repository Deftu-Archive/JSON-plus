package xyz.matthewtgm.json.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonApiHelper {

    /*public static <K extends String, V> JsonObject<K, V> getJsonObject(String url, boolean useAgent) {
        return JsonParser.parseObj(getJson(url, useAgent));
    }

    public static <K extends String, V> JsonObject<K, V> getJsonObject(String url) {
        return getJsonObject(url, true);
    }

    public static <T>JsonArray<T> getJsonArray(String url, boolean useAgent) {
        return JsonParser.parseArr(getJson(url, useAgent));
    }

    public static <T>JsonArray<T> getJsonArray(String url) {
        return getJsonArray(url, true);
    }

    public static String getJson(String url, boolean useAgent) {
        String ret;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            if (useAgent) connection.setRequestProperty("User-Agent", "JsonTGM (Mozilla Firefox)");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) response.append(line);
            ret = response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            ret = "";
        }
        return ret;
    }

    public static String getJson(String url) {
        return getJson(url, true);
    }*/

}