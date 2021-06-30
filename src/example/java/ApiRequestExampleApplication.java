import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.util.JsonApiHelper;

public class ApiRequestExampleApplication {

    public static ApiRequestExampleApplication instance = new ApiRequestExampleApplication();

    public void start() {
        JsonObject fetched = JsonApiHelper.getJsonObject("https://tools.learningcontainer.com/sample-json.json");
        System.out.println(fetched);
        System.out.println(fetched.getArray("phoneNumbers"));
    }

    public static void main(String[] args) {
        ApiRequestExampleApplication.instance.start();
    }

}