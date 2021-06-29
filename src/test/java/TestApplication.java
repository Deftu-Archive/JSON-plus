import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.parser.JsonParser;

public class TestApplication {

    public static final TestApplication instance = new TestApplication();

    public void start() {
        System.out.println("Parsed: " + JsonParser.parse(new JsonObject().add("world", "Hello!")));
        System.out.println(JsonParser.parse("[\"MatthewTGM\", \"Basilicous\", \"Ghqst\"]"));
    }

}