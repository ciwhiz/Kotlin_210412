// 24_코틀린표준라이브러리.kt
package ex24

import java.io.File
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

// 1. 조건 확인 함수
//  - 특정한 조건이 참이 아닐 경우 예외를 발생시킵니다.
//  1) check     ->  IllegalStateException
//  2) require   ->  IllegalArgumentException

fun logMessage(filename: String, message: String) {
    val f = File(filename)


    /*
    if (f.exists().not()) {
        throw IllegalStateException("file not found")
        // throw IllegalArgumentException("file not found")
    }
    */

    // require(f.exists())
    check(f.exists())

}


fun main() {
    logMessage("a.txt", "hello")
}