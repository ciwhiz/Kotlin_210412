// 15_고차함수2.kt
package ex15_2

import java.util.function.Predicate

// 변하지 않는 전체 알고리즘에서 변해야 하는 정책은 분리되어야 한다.
//  => 공통성과 가변성의 분리
//  => 함수에서 정책을 분리하는 2가지 방법
//    1) Java - 동작 파라미터화 설계
//       정책을 인터페이스 기반 클래스를 통해 분리한다.

//interface Predicate<E> {
//    fun test(e: E): Boolean
//}

fun filter(data: List<Int>, predicate: Predicate<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (e in data) {
        if (predicate.test(e))
            result.add(e)
    }
    return result
}


fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val result1 = filter(list, object : Predicate<Int> {
        override fun test(e: Int): Boolean = e % 2 == 1
    })
    println(result1)

    val result2 = filter(list, object : Predicate<Int> {
        override fun test(e: Int): Boolean = e % 2 == 0
    })
    println(result2)
}