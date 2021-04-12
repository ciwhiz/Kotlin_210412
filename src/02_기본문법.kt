// 02_기본문법.kt
package ex2

// 1. main 함수를 만드는 방법
// 2. 함수를 만드는 방법
//   1) 전역 함수를 만드는 것이 가능합니다.
//      Java - Arrays / Objects / Collections
//          : 정적 메소드를 모아놓은 클래스
//      Kotlin - 별도의 파일에 전역 함수를 모아놓으면 됩니다.

//   2) fun 함수이름(파라미터이름: 파라미터타입): 반환타입
//      Java void -> Kotlin Unit

//   3) Kotlin: OOP + FP(Functional Programming)
//      => 순수 함수(Pure Function)
//       : 함수의 입력이 동일하면, 결과도 동일하다.
//      => 불변 객체(Immutable Object)
//       : 객체가 생성된 이후에 값이 변경되지 않는다.

// 3. 변수 선언 방법
//   1) Java의 타입 시스템
//     - Primitive Type: int, double, char, byte, short ...
//     - Reference Type: class, enum, Array
//   2) Kotlin의 타입 시스템
//     - 모든 것이 객체(속성 / 메소드) 입니다.
//     - 강한 타입 제약을 가지고 있습니다.
//      : 암묵적인 타입 변환을 거의 허용하지 않습니다.

//   3) 변수 선언 방법 - 타입 추론
//     - var
//      : int n = 100
//     = var n: Int = 100
//     = var n = 100

//     - val
//      : final int n = 100
//     = val n: Int = 100
//     = val n = 100

// Unit: '값이 없음'을 나타내는 값 타입
fun print(): Unit {
}

fun add(a: Int, b: Int): Int {
    return a + b
}

// 1.3 버전부터는 args에 대한 부분을 사용하지 않을 때 생략이 가능합니다.
fun main() {
    // val n = 42
    // val l: Long = n.toLong()

    val n: Long = Int.MAX_VALUE.toLong() + 1
    val n2 = n.toInt()

    println(n)
    println(n2)
    // 42.toString()

    // println("Hello, Kotlin")
    // println(print())
}



