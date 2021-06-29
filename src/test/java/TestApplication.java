import xyz.matthewtgm.json.parser.JsonParser;

public class TestApplication {

    public static final TestApplication instance = new TestApplication();

    public void start() {
        System.out.println(JsonParser.parse("{\"hello\": \"world!\"}"));
        System.out.println(JsonParser.parse("[\"MatthewTGM\", \"Basilicous\", \"Ghqst\"]"));
    }

}