import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.entities.JsonPrimitive;
import xyz.matthewtgm.json.util.JsonHelper;

import java.util.Random;

public class DeepSearcherExampleApplication {

    public static DeepSearcherExampleApplication instance = new DeepSearcherExampleApplication();

    public void start() {
        JsonObject object = new JsonObject();
        object.add("random", new Random().nextInt());
        object.add("nested", new JsonObject()
                .add("data", new JsonObject()
                        .add("title", "MVP")));

        System.out.println(JsonHelper.deepSearchKey("title", object)); /* Returns true, only checks keys */
        System.out.println(JsonHelper.deepSearchValue(new JsonPrimitive("MVP"), object)); /* Returns true, only checks values. */
        System.out.println(JsonHelper.deepSearch("title", object)); /* Returns true, checks both keys and values. */
    }

    public static void main(String[] args) {
        DeepSearcherExampleApplication.instance.start();
    }

}