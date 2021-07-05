import xyz.matthewtgm.json.entities.JsonObject;

public class Colour {

    private int r, g, b, a;

    public Colour(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public int getRed() {
        return r;
    }

    public int getGreen() {
        return g;
    }

    public int getBlue() {
        return b;
    }

    public int getAlpha() {
        return a;
    }

    public void setRed(int r) {
        this.r = r;
    }

    public void setGreed(int g) {
        this.g = g;
    }

    public void setBlue(int b) {
        this.b = b;
    }

    public void setAlpha(int a) {
        this.a = a;
    }

    public String toString() {
        return new JsonObject().add("red", r).add("green", g).add("blue", b).add("alpha", a).getAsString();
    }

}