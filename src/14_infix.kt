// 14_infix.kt

package ex14

// 1. Function
// fun pair(key: Any, value: Any) = Pair(key, value)

// 2. Extension Function
// fun Any.pair(value: Any) = Pair(this, value)

// 3. 인자가 하나 뿐인 메소드 또는 확장 함수에 대해서 중위 함수(infix function)를 사용할 수 있습니다.
infix fun Any.pair(value: Any) = Pair(this, value)

fun main() {
    // Map의 구성 요소는 Pair 타입입니다.
    // val pair1 = Pair("name", "Tom")
    // val pair2 = Pair("age", 42)

    // val pair1 = pair("name", "Tom")
    // val pair2 = pair("age", 42)

    // val pair1 = "name".pair("Tom")
    // val pair2 = "age".pair(42)

    /*
    val pair1 = "name" pair "Tom"
    val pair2 = "age" pair 42

    val map = mapOf(
        pair1,
        pair2,
    )
    */

    val map = mapOf(
        "name" to "Tom",
        "age" to 42,
    )

    println(map)
}