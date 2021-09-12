package xyz.qalcyo.json.files;

import xyz.qalcyo.json.parser.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class JsonReader {

    private JsonReader() {}

    /**
     * @param name The name of the JSON file. (excluding the ".json" part.)
     * @param directory The directory where the JSON file is located.
     * @return The JSON file found in the location provided.
     * @author MatthewTGM
     * @since 2.0
     */
    public static <T> T read(String name, File directory) {
        try {
            if (name.endsWith(".json")) name = name.substring(0, name.indexOf(".json"));
            if (directory == null) directory = new File("./");
            if (!directory.exists()) directory.mkdirs();
            File file = new File(directory, name + ".json");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();
            reader.lines().forEach(builder::append);
            return (T) JsonParser.parse(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param name The name of the JSON file. (excluding the ".json" part.)
     * @return The JSON file found in the location provided.
     * @author MatthewTGM
     * @since 2.0
     */
    public static <T> T read(String name) {
        return read(name, null);
    }

}