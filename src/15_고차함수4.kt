// 15_고차함수4.kt
package ex15_4

// 1) 함수를 인자로 전달하는 함수
// => 다양한 시라니오 동작하는 함수의 코드 중복을 없앨 수 있다.

// 2) 함수를 반환하는 함수
// => 실행 시간에 함수를 생성할 수 있습니다.
//   - 함수 합성
//   - 커링

// () -> (String) -> String)
//  : -> 오른쪽 결합을 합니다.

/*
fun foo(): (String) -> String {
    return { str ->
        str.reversed()
    }
}

fun main() {
    // val fn: () -> (String) -> String = ::foo
    val result: (String) -> String = foo()  // 함수가 반환합니다.

    val result2: String = result("hello")
    println(result2)
}
*/

// (Int) -> Boolean
// fun isOdd(e: Int) = e % 2 == 1

// (Int) -> Boolean
// fun isEven(e: Int) = e % 2 == 0

// modulo의 함수를 통해 isOdd / isEven 실행시간에 생성해서 사용하고 싶다
fun modulo1(k: Int, r: Int): (Int) -> Boolean {
    return { e: Int ->
        e % k == r
    }
}

fun modulo(k: Int, r: Int): (Int) -> Boolean = { e: Int ->
    e % k == r
}

fun main() {
    // 아래 코드를 완성해보세요
    // val isOdd = modulo(2, 1)
    // val isEven = modulo(2, 0)

    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    // val result = list.filter(isOdd)

    val result1 = list.filter { e ->
        e % 2 == 1
    }

    // val result1 = list.filter(modulo(2, 1))
    println(result1)

    val result2 = list.filter(modulo(2, 0))
    println(result2)

    // modulo(2,1)(3) - 커링
}









