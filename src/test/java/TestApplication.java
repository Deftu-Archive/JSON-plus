import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.parser.JsonParser;

public class TestApplication {

    public static TestApplication instance = new TestApplication();

    public void start() {
        JsonParser.registerTypeAdapter(Colour.class, new ColourTypeAdapter());
        JsonObject object = new JsonObject();
        Colour colour = new Colour(255, 255, 0, 100);
        object.add("colour", colour);
        System.out.println(object);
        System.out.println(object.get("colour"));

        Colour colour1 = JsonParser.deserialize(Colour.class, object.get("colour"));
        System.out.println(colour1);
    }

}