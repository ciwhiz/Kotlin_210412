// 10_위임3.kt
package ex10_3

import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

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
//  => val에 대해서만 사용할 수 있습니다.
//  => 스레드 안전하게 생성되는 것을 보장합니다.
class User {
    // 2. lateinit var
    // lateinit var age: Int
    //  => 참조 타입에 대해서만 사용할 수 있습니다.
    //    - Int, Double, Long, Float

    // val heavyObject = HeavyObject()
    val heavyObject: HeavyObject by lazy {
        HeavyObject()
    }

    val age: Int by lazy {
        42
    }

    fun play() {
        heavyObject.play()
    }
}

/*
fun main() {
    val user = User()
    println("User object created")

    user.play()
}
*/

// 2. 프로퍼티의 값의 변경에 따라 수행되는 로직을 캡슐화하는 목적 - Delegates.observable
//    - KVO(Key-Value Observation)

class TextView {
    var text: String by Delegates.observable("") { property: KProperty<*>, oldValue: String, newValue: String ->
        println("$oldValue -> $newValue")
    }
}

fun main() {
    val tv = TextView()
    tv.text = "Tom"
    tv.text = "Bob"
}







