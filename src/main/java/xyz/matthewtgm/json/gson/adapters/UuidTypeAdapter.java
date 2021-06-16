package xyz.matthewtgm.json.gson.adapters;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.UUID;

public class UuidTypeAdapter implements JsonDeserializer<UUID>, JsonSerializer<UUID> {

    public UUID deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String uuid = json.getAsString();
        UUID ret;
        if (uuid.contains("-")) ret = UUID.fromString(uuid);
        else ret = UUID.fromString(uuid.substring(0, 8) + "-" + uuid.substring(8, 12) + "-" + uuid.substring(12, 16) + "-" + uuid.substring(16, 20) + "-" + uuid.substring(20, 32));
        return ret;
    }

    public JsonElement serialize(UUID src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }

}