package xyz.queffe.json.kotlin

import xyz.queffe.json.entities.CopyOnWriteJsonArray
import xyz.queffe.json.entities.JsonArray

fun jsonArray(unit: JsonArrayBuilder.() -> Unit): JsonArray =
    JsonArrayBuilder().apply(unit).build()

class JsonArrayBuilder {

    private var values: Array<Any?> = arrayOf()
    var concurrent = false

    fun values(vararg values: Any?) {
        this.values = values.asList().toTypedArray()
    }

    fun concurrency(state: Boolean) {
        this.concurrent = state
    }

    fun build(): JsonArray =
        (if (concurrent) CopyOnWriteJsonArray() else JsonArray()).addAll(*values)

    }