package ex15
// 고차 함수(Higher Order Function)
// : 함수를 인자로 전달 받거나 함수를 반환하는 함수

// 고차 함수
//  => 다양한 시나리오에 동작하는 함수의 코드 중복을 없앨 수 있다.
//  => 함수의 재사용성을 극대화할 수 있다.

// Kotlin
//   List<E> / Set<E> / Map<E>                          => Immutable
//              |
//   MutableList<E> / MutableSet<E> / MutableMap<E>     => Mutable

fun filterOdds(data: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (e in data) {
        if (e % 2 == 1)
            result.add(e)
    }
    return result
}

fun filterEvens(data: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (e in data) {
        if (e % 2 == 0)
            result.add(e)
    }
    return result
}

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val result1 = filterOdds(list)
    println(result1)

    val result2 = filterEvens(list)
    println(result2)
}