import xyz.matthewtgm.json.adaptation.TypeAdapter;
import xyz.matthewtgm.json.entities.JsonElement;
import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.entities.JsonPrimitive;
import xyz.matthewtgm.json.parser.JsonParser;

import java.lang.reflect.Type;
import java.util.UUID;

public class TypeAdapterExampleApplication {

    public static TypeAdapterExampleApplication instance = new TypeAdapterExampleApplication();

    public void start() {
        JsonParser.registerTypeAdapter(UUID.class, new UuidTypeAdapter());
        JsonObject uuidHolder = new JsonObject().add("uuid", UUID.randomUUID());
        System.out.println(uuidHolder.getAsString());
    }

    public static class UuidTypeAdapter implements TypeAdapter<UUID> {

        public JsonElement serialize(UUID source, Type type) {
            return new JsonPrimitive(source.toString());
        }

        public UUID deserialize(JsonElement json, Type type) {
            String uuid = json.getAsString();
            UUID ret;
            if (uuid.contains("-")) ret = UUID.fromString(uuid);
            else ret = UUID.fromString(uuid.substring(0, 8) + "-" + uuid.substring(8, 12) + "-" + uuid.substring(12, 16) + "-" + uuid.substring(16, 20) + "-" + uuid.substring(20, 32));
            return ret;
        }

    }

    public static void main(String[] args) {
        TypeAdapterExampleApplication.instance.start();
    }

}