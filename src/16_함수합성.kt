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

/*
fun compose(f: (String) -> Int, g: (Any) -> Int): (String) -> Int {
    return { x ->
        val y = f(x)
        val z = g(y)
        z
    }
}
*/

/*
fun compose(f: (String) -> Int, g: (Any) -> Int): (String) -> Int = { x ->
    g(f(x))
}

fun main() {
    val f: (String) -> Int = String::length
    val g: (Any) -> Int = Any::hashCode

    val h = compose(f, g)
    println(h("hello"))
}
*/

// 1. 일반화
/*
fun <A, B, C> compose(f: (A) -> B, g: (B) -> C): (A) -> C = { x ->
    g(f(x))
}
*/

// 2. 확장 함수 + 중위 함수
infix fun <A, B, C> ((A) -> B).compose(g: (B) -> C): (A) -> C = { x ->
    g(this(x))
}

fun main() {
    val f: (String) -> Int = String::length
    val g: (Any) -> Int = Any::hashCode

    // val h = compose(f, g)

    // val h = f.compose(g)
    val h = f compose g
    println(h("hello"))
}