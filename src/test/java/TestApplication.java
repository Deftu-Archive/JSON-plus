import org.junit.jupiter.api.*;
import xyz.matthewtgm.json.entities.ConcurrentJsonObject;
import xyz.matthewtgm.json.entities.CopyOnWriteJsonArray;
import xyz.matthewtgm.json.entities.JsonArray;
import xyz.matthewtgm.json.entities.JsonObject;

import static org.junit.jupiter.api.Assertions.*;

public class TestApplication {

    @Test
    @DisplayName("Json Element is aware of concurrency.")
    public void concurrency() {
        JsonArray array = new CopyOnWriteJsonArray();
        assertTrue(array.isConcurrent());

        JsonObject object = new ConcurrentJsonObject();
        assertTrue(object.isConcurrent());
    }

}