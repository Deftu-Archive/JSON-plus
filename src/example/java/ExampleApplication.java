import xyz.matthewtgm.json.entities.JsonArray;
import xyz.matthewtgm.json.entities.JsonObject;

public class ExampleApplication {

    public static ExampleApplication instance = new ExampleApplication();

    public void start() {
        JsonObject simpleObject = new JsonObject();
        simpleObject.add("Hello,", "world!");
        System.out.println(simpleObject);

        JsonArray simpleArray = new JsonArray();
        simpleArray.add("Hello World!");
        System.out.println(simpleArray);

        JsonObject builtObject = new JsonObject().add("Hi,", "Java!");
        System.out.println(builtObject);

        JsonArray builtArray = new JsonArray().add("Hi Java!");
        System.out.println(builtArray);
    }

    public static void main(String[] args) {
        ExampleApplication.instance.start();
    }

}