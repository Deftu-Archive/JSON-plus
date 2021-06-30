import xyz.matthewtgm.json.util.JsonHelper;

public class ValidatorExampleApplication {

    public static ValidatorExampleApplication instance = new ValidatorExampleApplication();

    public void start() {
        System.out.println(JsonHelper.isValidJson("{\"name\": \"Matthew\"}")); // Logs true.
        System.out.println(JsonHelper.isValidJson("name: Matthew")); // Logs false.
    }

    public static void main(String[] args) {
        ValidatorExampleApplication.instance.start();
    }

}