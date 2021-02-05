package ga.matthewtgm.json.files;

import ga.matthewtgm.json.base.Json;
import ga.matthewtgm.json.objects.JsonArray;
import ga.matthewtgm.json.objects.JsonObject;
import ga.matthewtgm.json.parsing.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class JsonReader {

    public static JsonObject readObj(String name, File directory) {
        try {
            if (!directory.exists()) {
                directory.mkdirs();
                Thread.sleep(1000);
            }
            File file = new File(directory, name + ".json");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();
            reader.lines().forEach(builder::append);
            return JsonParser.parseObj(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JsonArray readArr(String name, File directory) {
        try {
            if (!directory.exists()) {
                directory.mkdirs();
                Thread.sleep(1000);
            }
            File file = new File(directory, name + ".json");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();
            reader.lines().forEach(builder::append);
            return JsonParser.parseArr(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Json read(String name, File directory, Class<?> jsonClazz) {
        try {
            if (!directory.exists()) {
                directory.mkdirs();
                Thread.sleep(1000);
            }
            File file = new File(directory, name + ".json");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();
            reader.lines().forEach(builder::append);
            return JsonParser.parse(builder.toString(), jsonClazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}