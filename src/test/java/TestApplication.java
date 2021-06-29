import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.files.JsonReader;
import xyz.matthewtgm.json.files.JsonWriter;
import xyz.matthewtgm.json.parser.JsonParser;
import xyz.matthewtgm.json.util.JsonHelper;

import java.io.File;
import java.io.InputStream;
import java.util.Objects;

public class TestApplication {

    public static final TestApplication instance = new TestApplication();

    public void start() throws Exception {
        JsonWriter.write("data", new JsonObject().add("Hello", "world!"), new File("./"));

        System.out.println(JsonReader.read("data", new File("./")));

        System.out.println("-----");

        InputStream testJSONInputStream = TestApplication.class.getClassLoader().getResourceAsStream("test.json");
        byte[] testJSONBytes = new byte[Objects.requireNonNull(testJSONInputStream).available()];
        testJSONInputStream.read(testJSONBytes);
        JsonObject jsonObject = (JsonObject) JsonParser.parse(new String(testJSONBytes));
        System.out.println(((JsonObject) jsonObject.getAsArray("array").get(0)).getAsObject("test").getAsPrimitive("key"));

        System.out.println("----");

        System.out.println(JsonHelper.makePretty("{\"key\":\"test-value\",\"object\":{\"key\":\"test-value-in-object,}\"},\"array\":[\"test{\",\"test2:]\",\"test3\"]}", 2));
    }

}