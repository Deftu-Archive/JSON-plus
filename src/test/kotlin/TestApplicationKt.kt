import xyz.matthewtgm.json.kotlin.*

fun main() {
    val concurrentJsonObject = jsonObject(
        "name" to "gamer",
        "gaming" to "name"
    )
    println(concurrentJsonObject)
    println("name".toJsonPrimitive())
}