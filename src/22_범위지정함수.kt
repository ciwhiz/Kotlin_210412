// 22_범위지정함수.kt
package ex22

import java.lang.StringBuilder

// 1. let - Nullable
// 2. with / apply

/*
fun alphabet(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }
    return result.toString()
}
*/

// 수신 객체(this) 지정 람다
//  T.() -> R
inline fun <T, R> with1(receiver: T, block: T.() -> R): R {
    // return block(receiver)
    return receiver.block()
}

// with
fun alphabet_with(): String = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    return toString()
}

inline fun <T> T.apply1(block: T.() -> Unit): T {
    block()
    return this
}

// apply
fun alphabet(): String = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
}.toString()


//fun alphabet(): String = with(StringBuilder()) {
//    for (letter in 'A'..'Z') {
//        append(letter)
//    }
//    return toString()
//}


fun main() {
    // val result = alphabet()
    // println(result)
}