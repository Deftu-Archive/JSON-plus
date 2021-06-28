import xyz.matthewtgm.json.annotations.JsonSerialize;
import xyz.matthewtgm.json.annotations.JsonSerializeName;
import xyz.matthewtgm.json.objects.JsonObject;
import xyz.matthewtgm.json.util.JsonDeserializer;
import xyz.matthewtgm.json.util.JsonSerializer;

public class TestApplication {

    public static final TestApplication instance = new TestApplication();

    public void start() {
        JsonSerializer.serialize(new Data("MatthewTGM", "VIP"));

        Data deserialized = JsonDeserializer.deserialize("{\"player_name\":\"MatthewTGM\",\"title\":\"MVP\"}", Data.class);
        System.out.println(deserialized.name);
        System.out.println(deserialized.title);

        Data deserializedAsObject = JsonDeserializer.deserialize(new JsonObject<>().add("player_name", "Gamerboi69").add("title", "GAMER"), Data.class);
        System.out.println(deserializedAsObject.name);
        System.out.println(deserializedAsObject.title);
    }


    @JsonSerialize("data")
    public static class Data {
        @JsonSerializeName("player_name")
        public final String name;
        public final String title;
        public Data(String name, String title) {
            this.name = name;
            this.title = title;
        }
        public Data() {
            this("", "");
        }
    }

}