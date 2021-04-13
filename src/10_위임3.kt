// 10_위임3.kt
package ex10_3

import java.util.concurrent.TimeUnit

// 코틀린이 이미 제공하는 표준 프로퍼티 위임 객체가 있습니다.

class HeavyObject {
    init {
        println("HeavyObject creating!!")
        TimeUnit.SECONDS.sleep(2)
        println("HeavyObject created!!")
    }

    fun play() {
        println("HeavyObject play")
    }
}

// 1. lazy
//  => 프로퍼티 지연 초기화
//  => 프로퍼티가 객체 생성 시점에 초기화되는 것이 아니라, 처음 접근하는 시점에 생성하고 싶다.

class User {
    // val heavyObject = HeavyObject()
    val heavyObject: HeavyObject by lazy {
        HeavyObject()
    }

    fun play() {
        heavyObject.play()
    }
}

fun main() {
    val user = User()
    println("User object created")

    user.play()
}







