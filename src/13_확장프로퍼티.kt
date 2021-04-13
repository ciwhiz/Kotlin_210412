// 13_확장프로퍼티.kt
package ex13

// 프로퍼티
// 1. Backing field 있는 프로퍼티 - 별도의 저장 공간
//------------
// 2. Backing field 없는 프로퍼티 - 메소드  => 확장 프로퍼티

fun String.lastChar(): Char = this[length - 1]

// getter + setter: var
// getter         : val

val String.lastChar: Char
    get() = this[length - 1]

fun main() {
    // val r = "hello".lastChar()

    val r = "hello".lastChar
    println(r)
}