package xyz.matthewtgm.json.files;

import xyz.matthewtgm.json.base.Json;
import xyz.matthewtgm.json.objects.JsonArray;
import xyz.matthewtgm.json.objects.JsonObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {

    /**
     * Writes a file with the object specified as its contents
     *
     * @param object    the object being written
     * @param directory the directory to write to
     */
    public static void writeObj(String fileName, JsonObject object, File directory) {
        BufferedWriter writer = null;
        try {
            if (!directory.exists()) {
                directory.mkdirs();
                Thread.sleep(1000);
            }
            File file = new File(directory, fileName + ".json");
            if (!file.exists()) file.createNewFile();
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(object.toJson());
        } catch (Exception e) {
            e.printStackTrace();
            try {
                writer.flush();
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    /**
     * Writes a file with the object specified as its contents
     *
     * @param array     the object being written
     * @param directory the directory to write to
     */
    public static void writeArr(String fileName, JsonArray array, File directory) {
        BufferedWriter writer = null;
        try {
            if (!directory.exists()) {
                directory.mkdirs();
                Thread.sleep(1000);
            }
            File file = new File(directory, fileName + ".json");
            if (!file.exists()) file.createNewFile();
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(array.toJson());
        } catch (Exception e) {
            e.printStackTrace();
            try {
                writer.flush();
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    /**
     * Writes a file with the object specified as its contents
     *
     * @param json      the object being written
     * @param directory the directory to write to
     */
    public static void write(String fileName, Json json, File directory) {
        BufferedWriter writer = null;
        try {
            if (!directory.exists()) {
                directory.mkdirs();
                Thread.sleep(1000);
            }
            File file = new File(directory, fileName + ".json");
            if (!file.exists()) file.createNewFile();
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(json.toJson());
        } catch (Exception e) {
            e.printStackTrace();
            try {
                writer.flush();
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

}