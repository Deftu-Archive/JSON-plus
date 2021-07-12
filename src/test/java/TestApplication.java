import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.files.JsonWriter;

public class TestApplication {

    public static TestApplication instance = new TestApplication();

    public void start() {
        JsonObject object = new JsonObject();
        object.add("integer", 1);
        object.add("boolean", false);
        object.add("double", 0.3d);
        object.add("float", 4.2f);
        object.add("byte", (byte) 5);
        object.add("long", 678945678045678923L);
        object.add("short", (short) 32);
        object.add("char", 'c');
        object.add("string", "I'm a string!");

        System.out.println(object);
        JsonWriter.write("test", object);
    }

}