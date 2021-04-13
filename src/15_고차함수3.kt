// 15_고차함수3.kt
package ex15_3

// 정책 함수를 인자로 전달하면 됩니다.
fun isOdd(e: Int) = e % 2 == 1
fun isEven(e: Int) = e % 2 == 0

// (Int) -> Boolean

fun filter(data: List<Int>, predicate: (Int) -> Boolean): List<Int> {
    val result = mutableListOf<Int>()
    for (e in data) {
        if (predicate(e))
            result.add(e)
    }
    return result
}


fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val result1 = filter(list, ::isOdd)
    println(result1)

    val result2 = filter(list, ::isEven)
    println(result2)
}