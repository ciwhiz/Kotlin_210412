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

// Unit: '값이 없음'을 나타내는 값 타입
fun print(): Unit {
}

fun add(a: Int, b: Int): Int {
    return a + b
}

// 1.3 버전부터는 args에 대한 부분을 사용하지 않을 때 생략이 가능합니다.
fun main() {
    println("Hello, Kotlin")

    println(print())
}



