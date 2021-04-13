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

/*
fun <T> observable(initialValue: T, onChange: (property: KProperty<*>, oldValue: T, newValue: T) -> Unit): ReadWriteProperty<Any?, T> {
    return object : ObservableProperty<T>(initialValue) {
        override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) = onChange(property, oldValue, newValue)
    }
}
*/

// 2. 프로퍼티의 값의 변경에 따라 수행되는 로직을 캡슐화하는 목적 - Delegates.observable
//    - KVO(Key-Value Observation)
class TextView {
    var text: String by Delegates.observable("") { _: KProperty<*>, oldValue: String, newValue: String ->
        println("$oldValue -> $newValue")
    }
}

/*
fun main() {
    val tv = TextView()
    tv.text = "Tom"
    tv.text = "Bob"
}
*/

// 3. 조건에 부합되지 않는다면, 값이 변경되지 않도록 하고 싶다. - Delegates.vetoable
class Person {
    var name: String by Delegates.vetoable("Alice") { _, _, newValue ->
        newValue.length >= 3
    }
}

/*
fun main() {
    val person = Person()
    person.name = "A"

    println(person.name)
}
*/


/*
{ "x": 10, "y": 20 }
*/

// 4. KVC(Key-Value Coding)
//  : Map을 이용해서 프로퍼티의 값을 읽을 수 있습니다.
class Point(json: Map<String, Any>) {
    val x: Int by json
    val y: Int by json

    override fun toString(): String {
        return "Point{x=$x, y=$y}"
    }
}

class Player(json: Map<String, Any>) {
    val name: String by json
    val pos: Point by json
}

fun main() {
    val json = mapOf(
        "x" to 10,
        "y" to 20
    )

    val point = Point(json)
    println(point.x)
    println(point.y)

    val json2 = mapOf(
        "name" to "Tom",
        "pos" to point,
    )
    val player = Player(json2)
    println(player.name)
    println(player.pos)

}









