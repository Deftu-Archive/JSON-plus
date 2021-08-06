package xyz.matthewtgm.json.kotlin.builders

import xyz.matthewtgm.json.entities.ConcurrentJsonObject
import xyz.matthewtgm.json.entities.JsonElement
import xyz.matthewtgm.json.entities.JsonObject

fun jsonObject(lambda: JsonObjectBuilder.() -> Unit): JsonObject =
    JsonObjectBuilder().apply(lambda).build()

class JsonObjectBuilder {

    private var values: Map<String, JsonElement> = mapOf()
    private var concurrent = false

    fun values(lambda: () -> Map<String, JsonElement>) {
        this.values = lambda()
    }

    fun concurrency(lambda: () -> Boolean) {
        this.concurrent = lambda()
    }

    fun build(): JsonObject =
        (if (concurrent) ConcurrentJsonObject() else JsonObject()).addAll(values)


}