import xyz.matthewtgm.json.serialization.annotations.JsonSerialize;
import xyz.matthewtgm.json.serialization.annotations.JsonSerializeName;
import xyz.matthewtgm.json.objects.JsonArray;
import xyz.matthewtgm.json.objects.JsonObject;
import xyz.matthewtgm.json.serialization.JsonDeserializer;
import xyz.matthewtgm.json.serialization.JsonSerializer;

public class TestApplication {

    public static final TestApplication instance = new TestApplication();

    public void start() {
        JsonSerializer.serialize(new Data("MatthewTGM", "VIP"));
        System.out.println(JsonSerializer.create(new Data("Basilicous", "MVP++")));

        JsonObject<String, Object> serializedData = JsonSerializer.create(new Data("BatPersonArmor", "VIP+"));
        System.out.println(serializedData.toJson(serializedData, true));

        Data deserialized = JsonDeserializer.deserialize("{\"player_name\":\"MatthewTGM\",\"title\":\"MVP\"}", Data.class);
        System.out.println(deserialized.name);
        System.out.println(deserialized.title);

        Data deserializedAsObject = JsonDeserializer.deserialize(new JsonObject<>().add("player_name", "Gamerboi69").add("title", "GAMER"), Data.class);
        System.out.println(deserializedAsObject.name);
        System.out.println(deserializedAsObject.title);

        Information deserializedInfo = JsonDeserializer.deserialize(new JsonObject<>()
                .add("roles", new JsonArray<>()
                        .plus("gamer"))
                .add("colour", 3), Information.class);
        System.out.println(deserializedInfo.userRoles);
        System.out.println(deserializedInfo.colour);
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

    public static class Information {
        @JsonSerializeName("roles")
        public final JsonArray<String> userRoles;
        public final int colour;
        public Information(JsonArray<String> userRoles, int colour) {
            this.userRoles = userRoles;
            this.colour = colour;
        }
        public Information() {
            this(new JsonArray<>(), 0);
        }
    }

}