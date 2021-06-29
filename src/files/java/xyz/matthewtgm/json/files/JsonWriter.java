package xyz.matthewtgm.json.files;

import xyz.matthewtgm.json.entities.JsonElement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {

    public static void write(String fileName, JsonElement element, File directory, boolean pretty) {
        BufferedWriter writer = null;
        try {
            if (!directory.exists() && !directory.mkdirs()) throw new IllegalStateException("Directory didn't exist, failed to create it.");
            File file = new File(directory, fileName + ".json");
            if (!file.exists() && !file.createNewFile()) throw new IllegalStateException("Failed to create JSON file.");
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(/*pretty ? JsonHelper.makePretty(json) : json.toJson()*/element.toString());
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                } else System.err.println("Writer was null!");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                } else System.err.println("Writer was null!");
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    public static void write(String fileName, JsonElement element, File directory) {
        write(fileName, element, directory, false);
    }

}