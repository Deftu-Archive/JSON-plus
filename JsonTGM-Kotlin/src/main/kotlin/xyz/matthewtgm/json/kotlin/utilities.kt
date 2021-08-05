package xyz.matthewtgm.json.kotlin

import xyz.matthewtgm.json.entities.*

fun Any.toJsonPrimitive(): JsonPrimitive = JsonPrimitive(this)

fun jsonObject(vararg pairs: Pair<String, Any>): JsonObject {
    if (pairs.size > 0) {
        val obj = JsonObject()
        for (pair in pairs) {
            obj.add(pair.first, pair.second)
        }
        return obj
    }
    return JsonObject()
}
fun concurrentJsonObject(vararg pairs: Pair<String, JsonElement>): ConcurrentJsonObject {
    if (pairs.size > 0) {
        val obj = ConcurrentJsonObject()
        for (pair in pairs) {
            obj.add(pair.first, pair.second)
        }
        return obj
    }
    return ConcurrentJsonObject()
}
infix fun JsonArray.append(value: Any): JsonArray = add(value)