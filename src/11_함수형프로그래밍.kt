// 11_함수형프로그래밍.kt
package ex11

// 함수형 언어
// -> Haskell / Lisp / Erlang / Scala ...

// * 함수형 언어
// 1) 함수를 변수에 담을 수 있어야 한다.
// 2) 함수를 인자로 전달할 수 있어야 한다.
// 3) 함수를 반환할 수 있어야 한다.
// 4) 실행 시간에 함수를 생성할 수 있어야 한다.
// 5) 함수를 익명으로 생성할 수 있어야 한다.
// => 함수를 '일급 시민(First class citizen)'으로 취급한다.

// 1. 단일 표현식 함수
fun add1(a: Int, b: Int): Int {
    return a + b
}

// 위의 함수를 단일 표현식을 통해 표현하는 것이 가능합니다.
// fun add2(a: Int, b: Int): Int = a + b

fun add2(a: Int, b: Int) = a + b
// => 타입 추론이 가능합니다.
//  - 반환 타입에 대한 생략이 가능합니다.
//  - 반환 타입을 통해 표현식의 타입에서의 추론이 가능합니다.

// 2. 함수의 타입
//   : 함수의 타입은 '함수의 시그니처'에 의해 결정됩니다.
//    => 함수의 인자의 타입과 개수 / 반환타입 : 함수의 시그니처

// ::add1
//  - 전역 이름 공간의 함수를 참조한다.

// KFunction{ArgN}<Arg1, Arg2, Ret> - 코틀린의 함수가 JVM에서 취급되는 타입
// => 개념으로 이해해야 한다.

fun foo(a: Char, b: Double): String = ""

// (Int, Double, Float) -> Unit
fun goo(a: Int, b: Double, c: Float) {}

fun main() {
    val fn3: (Int, Double, Float) -> Unit = ::goo
    fn3(42, 3.14, 3.14f)
    
    val fn2: (Char, Double) -> String = ::foo
    fn2('A', 3.14)

    var fn: (Int, Int) -> Int = ::add1
    fn = ::add2

    fn(10, 20)

}




















