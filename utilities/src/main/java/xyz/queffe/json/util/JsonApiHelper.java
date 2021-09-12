package xyz.queffe.json.util;

import xyz.queffe.json.entities.JsonArray;
import xyz.queffe.json.entities.JsonObject;
import xyz.queffe.json.parser.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonApiHelper {

    private JsonApiHelper() {}

    public static JsonObject getJsonObject(String url, boolean useAgent) {
        return JsonParser.parse(getJson(url, useAgent)).getAsJsonObject();
    }

    public static JsonObject getJsonObject(String url) {
        return getJsonObject(url, true);
    }

    public static JsonArray getJsonArray(String url, boolean useAgent) {
        return JsonParser.parse(getJson(url, useAgent)).getAsJsonArray();
    }

    public static JsonArray getJsonArray(String url) {
        return getJsonArray(url, true);
    }

    public static String getJson(String url, boolean useAgent) {
        String ret;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            if (useAgent)
                connection.setRequestProperty("User-Agent", "JsonTGM (Mozilla Firefox)");
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
    }

}