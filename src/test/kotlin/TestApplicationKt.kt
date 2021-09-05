import xyz.deftu.json.kotlin.jsonArray
import xyz.deftu.json.kotlin.jsonObject

fun main() {
    println(jsonArray {
        values("Hello,", "world!")
        concurrency(true)
    })

    println(jsonObject {
        values("Hello," to "world!")
        concurrent = true
    })
}