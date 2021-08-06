package xyz.matthewtgm.json.kotlin

import xyz.matthewtgm.json.entities.*

fun Any.toJsonElement(): JsonElement = JsonElement.fromRef(this)
fun <T : JsonElement> Any.toJson(): T = JsonElement.fromRef(this)

infix fun JsonArray.append(value: Any): JsonArray = add(value)