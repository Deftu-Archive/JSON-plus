package xyz.qalcyo.json.kotlin

import xyz.qalcyo.json.entities.ConcurrentJsonObject
import xyz.qalcyo.json.entities.JsonElement
import xyz.qalcyo.json.entities.JsonObject
import xyz.qalcyo.json.parser.JsonParser

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