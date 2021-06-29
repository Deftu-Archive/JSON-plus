package xyz.matthewtgm.json.files;

import xyz.matthewtgm.json.entities.JsonElement;
import xyz.matthewtgm.json.parser.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class JsonReader {

    public static JsonElement read(String name, File directory) {
        try {
            if (!directory.exists()) directory.mkdirs();
            File file = new File(directory, name + ".json");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();
            reader.lines().forEach(builder::append);
            return JsonParser.parse(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}