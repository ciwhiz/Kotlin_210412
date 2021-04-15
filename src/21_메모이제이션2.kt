// 21_메모이제이션2.kt
package ex21_2

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlin.random.Random


// JSON
data class User(val name: String, val age: Int)
data class Car(val name: String, val color: Int, val speed: Int)

// toJSON()
//  (T) -> String
fun <T> T.toJSON(): String {
    println("toJSON()")
    val gson = Gson()
    return gson.toJson(this)
}

// OCP: Open-Close Principle
//  - 개방 폐쇄의 원칙
//  - 코드는 수정에는 닫혀 있고, 확장에는 열려 있어야 한다.
//  - 새로운 기능이 추가되어도 기존 코드는 수정되면 안된다.


// Design Pattern - Flyweight Pattern
//  의도: 속성이 동일한 객체는 공유하자.
//    - '불변 객체' 를 대상으로 사용해야 한다.

// 기존 함수에 '메모이제이션'의 기능을 추가하는 함수 - "고차함수"
//  Map<K, V>
//   K: equals / hashCode

// 재귀적인 코드에 대해서는 제대로 동작하지 않습니다.
fun <A, B> ((A) -> B).memoized(): (A) -> B {
    val cache = mutableMapOf<A, B>()
    return { k ->
        cache.getOrPut(k) {
            this(k)
        }
    }
}

// '메모이제이션'
// 순수 함수(Pure Function): 참조적 투명성
// => 인자의 값이 동일하면, 결과도 동일하다.

fun foo(n: Int): String {
    println("foo")
    return "$n"
}

// 순수 함수 X - 메모이제이션을 적용할 수 없다.
val random = Random(1024)
fun goo(n: Int): Int {
    return random.nextInt()
}

/*
fun main() {
    println(goo(10))
    println(goo(10))
    println(goo(10))

    // foo(10)
    // foo(10)
    // foo(10)

    val mfoo = ::foo.memoized()
    mfoo(10)
    mfoo(10)
    mfoo(10)


    val user = User("Tom", 42)
    val user2 = User("Tom", 42)
    // println(user.toJSON())

    val userToJSON = Any::toJSON.memoized()
    println(userToJSON(user))
    println(userToJSON(user2))
    println(userToJSON(user))
    println(userToJSON(user2))
    println(userToJSON(user))

    val carToJSON = Any::toJSON.memoized()
    val car = Car("Sonata", 255, 100)
    println(carToJSON(car))
    println(carToJSON(car))
    println(carToJSON(car))
    // println(car.toJSON())

}
*/

// Gson(Google JSON Serialization library)
/*
fun main() {
    val user = User("Tom", 42)

    // val gson = Gson()
    val gson = GsonBuilder()
        .setPrettyPrinting()
        .create()
    val json = gson.toJson(user)

    println(json)
}
*/

// Any -> String
fun <T> toJSON2(user: T): String {
    println("toJSON()")
    val gson = Gson()
    return gson.toJson(user)
}

fun main() {
    val gson = GsonBuilder()
        .setPrettyPrinting()
        .disableHtmlEscaping()
        .enableComplexMapKeySerialization()
        .create()

    // Kotlin-Style
    val gson2 = GsonBuilder().apply {
        setPrettyPrinting()
        disableHtmlEscaping()
        enableComplexMapKeySerialization()
    }.create()


    val fn: (Any) -> String = ::toJSON2
    val mfn = fn.memoized()

    val user = User("Tom", 42)
    mfn(user)
    mfn(user)
    mfn(user)
}
