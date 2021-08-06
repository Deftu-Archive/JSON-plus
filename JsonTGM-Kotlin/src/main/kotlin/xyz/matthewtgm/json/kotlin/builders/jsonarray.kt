package xyz.matthewtgm.json.kotlin.builders

import xyz.matthewtgm.json.entities.*

fun jsonArray(lambda: JsonArrayBuilder.() -> Unit): JsonArray =
    JsonArrayBuilder().apply(lambda).build()

class JsonArrayBuilder {

    private var values: Array<Any?> = arrayOf()
    private var concurrent = false

    fun values(vararg values: Any?) {
        this.values = values.asList().toTypedArray()
    }

    fun concurrency(lambda: () -> Boolean) {
        this.concurrent = lambda()
    }

    fun build(): JsonArray =
        (if (concurrent) CopyOnWriteJsonArray() else JsonArray()).addAll(*values)

}