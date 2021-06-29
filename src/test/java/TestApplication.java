import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.files.JsonReader;
import xyz.matthewtgm.json.files.JsonWriter;

import java.io.File;

public class TestApplication {

    public static final TestApplication instance = new TestApplication();

    public void start() {
        JsonWriter.write("data", new JsonObject().add("Hello", "world!"), new File("./"));

        System.out.println(JsonReader.read("data", new File("./")));
    }

}