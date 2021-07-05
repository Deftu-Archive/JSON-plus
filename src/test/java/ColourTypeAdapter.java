import xyz.matthewtgm.json.adaptation.TypeAdapter;
import xyz.matthewtgm.json.entities.JsonElement;
import xyz.matthewtgm.json.entities.JsonObject;

public class ColourTypeAdapter implements TypeAdapter<Colour> {

    public JsonElement serialize(Colour source) {
        System.out.println(source);
        return new JsonObject().add("red", source.getRed()).add("green", source.getGreen()).add("blue", source.getBlue()).add("alpha", source.getAlpha());
    }

    public Colour deserialize(JsonElement json) {
        JsonObject object = (JsonObject) json;
        System.out.println(object);
        return new Colour(object.get("red").getAsInt(), object.get("green").getAsInt(), object.get("blue").getAsInt(), object.get("alpha").getAsInt());
    }

}