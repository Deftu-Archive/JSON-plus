package xyz.matthewtgm.json.exceptions;

public class JsonParseException extends Exception {
    public JsonParseException() {
        super();
    }
    public JsonParseException(String reason) {
        super(reason);
    }
    public JsonParseException(Throwable cause) {
        super(cause);
    }
}