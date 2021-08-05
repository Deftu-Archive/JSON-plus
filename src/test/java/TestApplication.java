import xyz.matthewtgm.json.entities.CopyOnWriteJsonArray;
import xyz.matthewtgm.json.entities.JsonArray;
import xyz.matthewtgm.json.entities.JsonElement;
import xyz.matthewtgm.json.entities.JsonPrimitive;

public class TestApplication {

    public static TestApplication instance = new TestApplication();

    public void start() {
        JsonArray array = new CopyOnWriteJsonArray();
        array.add("element");
        array.add("1");
        if (!array.isConcurrent())
            throw new RuntimeException("no");
        System.out.println(array);
        for (JsonElement element : array) {
            if (!element.isJsonPrimitive())
                return;
            JsonPrimitive primitive = element.getAsJsonPrimitive();
            if (!primitive.isString())
                continue;
            String val = primitive.getAsString();
            if (val.equals("element")) {
                array.add("fun");
            }
        }
        System.out.println(array);
    }

    public static void main(String[] args) {
        instance.start();
    }

}