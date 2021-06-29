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
            writer.write(pretty ? makePretty(element.toString(), 2) : element.toString());
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