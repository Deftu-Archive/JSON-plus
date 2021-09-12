package xyz.queffe.json.kotlin

import xyz.queffe.json.entities.ConcurrentJsonObject
import xyz.queffe.json.entities.JsonElement
import xyz.queffe.json.entities.JsonObject
import xyz.queffe.json.parser.JsonParser

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