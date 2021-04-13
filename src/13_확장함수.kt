package ex13_3

import ex13.lastChar as lc  // aliasing

val String.lastChar: Char
    get() = 'C'

fun main() {
    "hello".lastChar
    "hello".lc()
}