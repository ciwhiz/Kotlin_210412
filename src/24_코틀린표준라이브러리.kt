// 24_코틀린표준라이브러리.kt
package ex24

import java.io.File
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

// 1. 조건 확인 함수
//  - 특정한 조건이 참이 아닐 경우 예외를 발생시킵니다.
//  1) check     ->  IllegalStateException
//  2) require   ->  IllegalArgumentException
fun logMessage(filename: String, message: String?) {
    val f = File(filename)

    /*
    if (f.exists().not()) {
        throw IllegalStateException("file not found")
        // throw IllegalArgumentException("file not found")
    }
    */

    requireNotNull(message)
    val m: String = message

    // require(f.exists())
    check(f.exists()) {
        println("hello")
        "File not found"
    }
}

// 2. 명시적인 실행 종료 함수
//  - Unit: 결과가 존재하지 않음을 나타내는 결과
//  - Nothing: 결과가 발생하지 않는다.
fun foo(): Nothing {
    error("error message")
    // throw IllegalStateException("error!")
}

fun main() {
    foo()

    // logMessage("a.txt", null)
}