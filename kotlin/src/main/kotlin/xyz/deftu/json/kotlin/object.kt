package xyz.deftu.json.kotlin

import xyz.deftu.json.entities.ConcurrentJsonObject
import xyz.deftu.json.entities.JsonElement
import xyz.deftu.json.entities.JsonObject
import xyz.deftu.json.parser.JsonParser

fun jsonObject(lambda: JsonObjectBuilder.() -> Unit): JsonObject =
    JsonObjectBuilder().apply(lambda).build()

class JsonObjectBuilder {

    private var values: Map<String, JsonElement> = mapOf()
    var concurrent = false

    fun values(vararg values: Pair<String, Any?>) {
        this.values = mapOf(*(values.map { Pair(it.first, JsonParser.parse(it.second.toString())) }).toTypedArray())
    }

    fun concurrency(state: Boolean) {
        this.concurrent = state
    }

    fun build(): JsonObject =
        (if (concurrent) ConcurrentJsonObject() else JsonObject()).addAll(values)


}