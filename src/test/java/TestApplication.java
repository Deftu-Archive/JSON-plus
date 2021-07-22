import xyz.matthewtgm.json.entities.JsonArray;
import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.parser.JsonParser;

public class TestApplication {

    public static TestApplication instance = new TestApplication();

    public void start() {
        JsonObject object = new JsonObject();
        System.out.println(object.getOrDefault("gamer", "gaming!"));
        object.add("gamer", "ez");
        System.out.println(object.getOrDefault("gamer", ":("));
    }

}