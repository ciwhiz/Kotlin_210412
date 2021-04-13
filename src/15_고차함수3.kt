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

    // 1. 익명 함수
    var result = filter(list, fun(e: Int): Boolean {
        return e % 2 == 1
    })
    println(result)

    // 2. 익명 함수 - 단일 표현식
    result = filter(list, fun(e: Int) = e % 2 == 1)
    println(result)

    // 3. 코틀린은 "람다 표현식"라는 개념이 별도로 존재합니다.
    //  - 람다 표현식(Expression): 실행 가능한 코드 블록(함수로 취급하지 않습니다)
    //    : 블록의 마지막 라인의 표현식에 의해 전체 결과가 결정됩니다.
    result = filter(list, { e: Int ->
        e % 2 == 1
    })

    // 4. 람다 - 타입 추론이 가능합니다
    result = filter(list, { e ->
        e % 2 == 1
    })

    // 5. 람다 - Trailing Lambda
    //  : 함수의 마지막 인자 함수인 경우, 람다 표현식의 블록을 함수 호출 괄호 밖에 사용할 수 있습니다.
    result = filter(list) { e ->
        e % 2 == 1
    }

    // 5. 람다 - 인자가 한개인 경우, it 키워드를 통해 인자를 참조할 수 있습니다.
    result = filter(list) { it % 2 == 1 }

    println(result)
}










