import xyz.qalcyo.json.kotlin.jsonArray
import xyz.qalcyo.json.kotlin.jsonObject

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