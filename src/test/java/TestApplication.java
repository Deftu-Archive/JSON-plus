import xyz.matthewtgm.json.entities.JsonArray;
import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.parser.JsonParser;

public class TestApplication {

    public static TestApplication instance = new TestApplication();

    public void start() {
        JsonArray array = new JsonArray();
        array.add("gaming");

        System.out.println(array.has("gaming"));
        System.out.println(array.has("not_gaming"));
    }

}