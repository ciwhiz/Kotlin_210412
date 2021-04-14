// 21_메모이제이션.kt
package ex21

// 메모이제이션: 동일한 인자를 함수의 결과를 캐시해서, 계산의 성능을 올리는 방법
// => 동적 계획법

/*
fun fib(k: Int): Long = when (k) {
    0, 1 -> 1
    else -> fib(k - 1) + fib(k - 2)
}
*/

/*
val cache = mutableMapOf<Int, Long>()
fun fib(k: Int): Long = when (k) {
    0, 1 -> 1
    else -> {
        val result = cache[k]

        if (result != null) {
            result
        } else {
            val n = fib(k - 1) + fib(k - 2)
            cache[k] = n
            n
        }
    }
}
*/

val cache = mutableMapOf<Int, Long>()
fun fib(k: Int): Long = cache.getOrPut(k) {
    // 값이 존재하지 않을 경우 수행되는 로직
    when (k) {
        0, 1 -> 1
        else -> fib(k - 1) + fib(k - 2)
    }
}


fun main() {
    val result = fib(100)
    println(result)
}