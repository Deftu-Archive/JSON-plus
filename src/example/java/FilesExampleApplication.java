import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.files.JsonReader;
import xyz.matthewtgm.json.files.JsonWriter;

public class FilesExampleApplication {

    public static FilesExampleApplication instance = new FilesExampleApplication();

    public void start() {
        JsonObject savedObject = new JsonObject();
        savedObject.add("data", "data_object");
        JsonWriter.write("saved", savedObject, true);

        System.out.println(JsonReader.read("saved"));
    }

    public static void main(String[] args) {
        FilesExampleApplication.instance.start();
    }

}