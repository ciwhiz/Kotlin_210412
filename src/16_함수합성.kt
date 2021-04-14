// 16_함수합성
package ex16

// f(x) -> y
// g(y) -> z

// x -> f(x) -> y -> g(y) -> z
// x -> f(x)*g(y) -> z

/*
fun main() {
    val f: (String) -> Int = String::length
    val g: (Any) -> Int = Any::hashCode

    val x = "hello"
    val y = f(x)
    val z = g(y)
    println(z)
}
*/

fun compose(f: (String) -> Int, g: (Any) -> Int): (String) -> Int {
    return { x ->
        val y = f(x)
        val z = g(y)
        z
    }
}

fun main() {
    val f: (String) -> Int = String::length
    val g: (Any) -> Int = Any::hashCode

    val h = compose(f, g)
    println(h("hello"))
}
