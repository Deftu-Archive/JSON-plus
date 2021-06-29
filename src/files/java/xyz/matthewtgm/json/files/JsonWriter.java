package xyz.matthewtgm.json.files;

/*import xyz.matthewtgm.json.base.Json;
import xyz.matthewtgm.json.objects.JsonArray;
import xyz.matthewtgm.json.objects.JsonObject;
import xyz.matthewtgm.json.util.JsonHelper;*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {
/*

    @Deprecated
    public static void writeObj(String fileName, JsonObject<?, ?> object, File directory, boolean pretty) {
        write(fileName, object, directory, pretty);
    }

    @Deprecated
    public static void writeArr(String fileName, JsonArray<?> array, File directory, boolean pretty) {
        write(fileName, array, directory, pretty);
    }

    public static void write(String fileName, Json json, File directory, boolean pretty) {
        BufferedWriter writer = null;
        try {
            if (!directory.exists() && !directory.mkdirs()) throw new IllegalStateException("Directory didn't exist, failed to create it.");
            File file = new File(directory, fileName + ".json");
            if (!file.exists() && !file.createNewFile()) throw new IllegalStateException("Failed to create JSON file.");
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(pretty ? JsonHelper.makePretty(json) : json.toJson());
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

    @Deprecated
    public static void writeObj(String fileName, JsonObject object, File directory) {
        writeObj(fileName, object, directory, false);
    }

    @Deprecated
    public static void writeArr(String fileName, JsonArray array, File directory) {
        writeArr(fileName, array, directory, false);
    }

    public static void write(String fileName, Json json, File directory) {
        write(fileName, json, directory, false);
    }*/

}