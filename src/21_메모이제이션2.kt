// 21_메모이제이션2.kt
package ex21_2

import com.google.gson.Gson


// JSON
data class User(val name: String, val age: Int)
data class Car(val name: String, val color: Int, val speed: Int)

// toJSON()
fun <T> T.toJSON(): String {
    val gson = Gson()
    return gson.toJson(this)
}

// 기존 함수에 메모이제이션의 기능을 추가하는 함수 - "고차함수"
fun <A, B> ((A) -> B).memoized(): (A) -> B {
    val cache = mutableMapOf<A, B>()
    return { k ->
        cache.getOrPut(k) {
            this(k)
        }
    }
}


fun main() {
    val user = User("Tom", 42)
    val car = Car("Sonata", 255, 100)

    println(user.toJSON())
    println(car.toJSON())
}


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