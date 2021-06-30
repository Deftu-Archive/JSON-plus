import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.serialization.JsonDeserializer;
import xyz.matthewtgm.json.serialization.JsonSerializer;
import xyz.matthewtgm.json.serialization.annotations.JsonSerialize;
import xyz.matthewtgm.json.serialization.annotations.JsonSerializeExcluded;
import xyz.matthewtgm.json.serialization.annotations.JsonSerializeName;

public class AdvancedExampleApplication {

    public static AdvancedExampleApplication instance = new AdvancedExampleApplication();

    public void start() {
        // Serialize the object to a file.
        JsonSerializer.serialize(new SerializedData("MatthewTGM", "VIP", false), SerializedData.class);
        // Serialize the object to a file without defining it's type.
        JsonSerializer.serialize(new SerializedData("MatthewTGM", "VIP++", false));

        // Create an object from a class using the serializer.
        System.out.println(JsonSerializer.create(new SerializedData("MatthewTGM", "VIP", false), SerializedData.class));
        // Create an object from a class without defining it's type.
        System.out.println(JsonSerializer.create(new SerializedData("MatthewTGM", "VIP", false)));

        // Deserialize an object to a class.
        SerializedData deserialized = JsonDeserializer.deserialize(new JsonObject().add("name", "MatthewTGM").add("rank", "MVP"), SerializedData.class);
        System.out.println(String.format("%s | %s | %s", deserialized.name, deserialized.title, deserialized.online));
    }

    @JsonSerialize("data") /* The annotation is not required for deserialization and the create method for serialization. */
    public static class SerializedData {
        public final String name;
        @JsonSerializeName("rank") /* Makes both the serializer and deserializer recognize the object element name as rank, and sets this to it's value. */
        public final String title;
        @JsonSerializeExcluded /* Defines whether or not this field will be excluded from the serialization and deserialization process. */
        public final boolean online;
        public SerializedData(String name, String title, boolean online) {
            this.name = name;
            this.title = title;
            this.online = online;
        }
        public SerializedData() { /* An empty constructor is REQUIRED for the deserializer to work. */
            this("", "", false);
        }
    }

    public static void main(String[] args) {
        AdvancedExampleApplication.instance.start();
    }

}