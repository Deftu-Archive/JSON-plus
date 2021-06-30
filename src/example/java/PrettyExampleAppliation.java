import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.util.JsonHelper;

public class PrettyExampleAppliation {

    public static PrettyExampleAppliation instance = new PrettyExampleAppliation();

    public void start() {
        JsonObject object = new JsonObject().add("name", "Matthew").add("language", "Java");
        String prettified = JsonHelper.makePretty(object, 2);
        System.out.println(prettified);
        System.out.println(JsonHelper.makeUnpretty(prettified));
    }

    public static void main(String[] args) {
        PrettyExampleAppliation.instance.start();
    }

}