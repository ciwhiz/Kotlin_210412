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

