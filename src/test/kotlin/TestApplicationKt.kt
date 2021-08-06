import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import xyz.matthewtgm.json.kotlin.builders.jsonArray
import xyz.matthewtgm.json.kotlin.builders.jsonObject
import xyz.matthewtgm.json.parser.JsonParser

class TestApplicationKt {

    @Test
    @DisplayName("Json Parser is correct.")
    fun parser() {
        val original = jsonObject {
            values(
                "test_string" to "hello, world!",
                "test_int" to 5,
                "test_float" to 5f,
                "test_double" to 5.0,
                "test_long" to 5L,
                "test_char" to 'a',
                "test_array" to jsonArray {
                    values(
                        "hello, world!",
                        5,
                        5f,
                        5.0,
                        5L,
                        'a'
                    )
                },
                "test_object" to jsonObject {
                    values(
                        "test_string" to "hello, world!",
                        "test_int" to 5,
                        "test_float" to 5f,
                        "test_double" to 5.0,
                        "test_long" to 5L,
                        "test_char" to 'a',
                        "test_array" to jsonArray {
                            values(
                                "hello, world!",
                                5,
                                5f,
                                5.0,
                                5L,
                                'a'
                            )
                        }
                    )
                }
            )
        }.toString()

        assert(original.contentEquals(JsonParser.parse(original).toString()))
    }

}
