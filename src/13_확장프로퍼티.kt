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

// StringBuilder가 외부에 공개하는 프로퍼티와 메소드만 사용 가능합니다.
var StringBuilder.lastChar: Char
    get() = this[length - 1]
    set(value) = setCharAt(length - 1, value)

fun main() {
    // val r = "hello".lastChar()
    val r = "hello".lastChar
    println(r)

    val sb = StringBuilder("hello")
    sb.lastChar = 'x'
    println(sb.toString())
}

// 확장 함수 / 확장 프로퍼티
// - 주의할 점: 함부로 작성하는 것이 좋지 않습니다.




