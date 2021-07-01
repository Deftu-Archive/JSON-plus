import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.util.JsonApiHelper;
import xyz.matthewtgm.json.util.JsonHelper;

public class TestApplication {

    public static TestApplication instance = new TestApplication();

    public void start() {
        JsonObject object = new JsonObject(JsonApiHelper.getJsonObject("https://static.sk1er.club/autogg/regex_triggers_new.json"));
        System.out.println(JsonHelper.makePretty(object, 4));
        System.out.println(JsonHelper.makePretty(object.getObject("servers").getObject("^(?:.+\\\\.)?hypixel\\\\.(?:net|io)$").getObject("gg_triggers").getArray("triggers"), 4));
    }

}