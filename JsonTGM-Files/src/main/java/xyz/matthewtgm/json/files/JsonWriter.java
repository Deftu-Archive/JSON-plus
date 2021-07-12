package xyz.matthewtgm.json.files;

import xyz.matthewtgm.json.entities.JsonElement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {

    private JsonWriter() {}

    /**
     * Writes the JSON element provided to a file.
     * @param fileName The name of the file. (Excluding the ".json" part.)
     * @param element The element to write.
     * @param directory The directory where the file must be written to.
     * @param pretty Whether or not the written JSON is "pretty".
     * @author MatthewTGM
     * @since 2.0
     */
    public static void write(String fileName, JsonElement element, File directory, boolean pretty) {
        if (fileName.endsWith(".json")) fileName = fileName.substring(0, fileName.indexOf(".json"));
        if (directory == null) directory = new File("./");
        BufferedWriter writer = null;
        try {
            if (!directory.exists() && !directory.mkdirs()) throw new IllegalStateException("Directory didn't exist, failed to create it.");
            File file = new File(directory, fileName + ".json");
            if (!file.exists() && !file.createNewFile()) throw new IllegalStateException("Failed to create JSON file.");
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(pretty ? makePretty(element.toString(), 4) : element.toString());
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

    /**
     * Writes the JSON element provided to a file.
     * @param fileName The name of the file. (Excluding the ".xyz.matthewtgm.json" part.)
     * @param element The element to write.
     * @param directory The directory where the file must be written to.
     * @author MatthewTGM
     * @since 2.0
     */
    public static void write(String fileName, JsonElement element, File directory) {
        write(fileName, element, directory, true);
    }

    /**
     * Writes the JSON element provided to a file.
     * @param fileName The name of the file. (Excluding the ".json" part.)
     * @param element The element to write.
     * @author MatthewTGM
     * @since 2.0
     */
    public static void write(String fileName, JsonElement element, boolean pretty) {
        write(fileName, element, null, pretty);
    }

    /**
     * Writes the JSON element provided to a file.
     * @param fileName The name of the file. (Excluding the ".json" part.)
     * @param element The element to write.
     * @author MatthewTGM
     * @since 2.0
     */
    public static void write(String fileName, JsonElement element) {
        write(fileName, element, null, true);
    }

    /**
     * @param json The JSON string to prettify.
     * @param indent The indent level.
     * @return The prettified JSON string.
     * @author Danterus
     * @since 2.0
     */
    private static String makePretty(String json, int indent) {
        StringBuilder result = new StringBuilder();
        boolean isInQuote = false;
        int currentIndent = 0;

        for(char c : makeUnpretty(json).toCharArray()) {
            if(c == '"') isInQuote = !isInQuote;
            if(!isInQuote && (c == '{' || c == '[')) {
                currentIndent += indent;
                result.append(c);
                result.append('\n');
                for(int i = 0; i < currentIndent; i++) result.append(" ");
            } else if(!isInQuote && (c == '}' || c == ']')) {
                currentIndent -= indent;
                result.append('\n');
                for(int i = 0; i < currentIndent; i++) result.append(" ");
                result.append(c);
            } else if(!isInQuote && c == ',') {
                result.append(c).append('\n');
                for(int i = 0; i < currentIndent; i++) result.append(" ");
            } else if(!isInQuote && c == ':') result.append(c).append(" ");
            else result.append(c);
        }
        return result.toString();
    }

    /**
     * @param json The JSON string to unprettify.
     * @return The unprettified JSON string.
     * @author Danterus
     * @since 2.0
     */
    private static String makeUnpretty(String json) {
        StringBuilder result = new StringBuilder();
        boolean isInQuote = false;

        for(char c : json.toCharArray()) {
            if(c == '"') isInQuote = !isInQuote;
            if(isInQuote || !(c == '\n' || c == ' ')) result.append(c);
        }
        return result.toString();
    }

}