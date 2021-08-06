package xyz.matthewtgm.json.kotlin.builders

import xyz.matthewtgm.json.entities.*

fun jsonArray(lambda: JsonArrayBuilder.() -> Unit): JsonArray =
    JsonArrayBuilder().apply(lambda).build()

class JsonArrayBuilder {

    private var values: Array<JsonElement> = arrayOf()
    private var concurrent = false

    fun values(lambda: () -> Array<JsonElement>) {
        this.values = lambda()
    }

    fun concurrency(lambda: () -> Boolean) {
        this.concurrent = lambda()
    }

    fun build(): JsonArray =
        (if (concurrent) CopyOnWriteJsonArray() else JsonArray()).fill(*values)

}