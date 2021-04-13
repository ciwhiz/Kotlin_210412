// 09_object2.kt
package ex9_2

import java.time.LocalDateTime

//  2) companion object(동반 객체)
//  => 코틀린에서는 static 키워드를 제공하지 않습니다.
//     "정적 메소드 / 정적 프로퍼티" 를 구현하기 위해서는 동반 객체를 이용해야 합니다.
/*
class User {
    // private static final String NAME = "TOM";
    // public static String getName() { return NAME; }

    companion object {
        val NAME = "TOM"

        fun getName(): String {
            return NAME
        }
    }
}

fun main() {
    println(User.NAME)
    println(User.getName())
}
*/

// 활용 1. 정적 팩토리 메소드
//     => 생성자 역활을 대체하는 목적으로 사용한다.
//     "생성자 한계"
//     1) 이름이 클래스 이름과 동일하다. - 의도를 표현하기 어렵다.
//     2) 객체 생성의 정책을 변경하기 어렵다.
/*
class User private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String): User {
            return User(email.substringBefore("@"))
        }

        fun newFacebookUser(accountId: String): User {
            return User("facebook@$accountId")
        }
    }
}

// Java8 - LocalDateTime / LocalDate
fun main() {
    val now = LocalDateTime.now()
    println(now)

    // val user1 = User("hello@gmail.com")
    val user1 = User.newSubscribingUser("hello@gmail.com")
    println(user1.nickname)

    // val user2 = User("1231375848120ddx")
    val user2 = User.newFacebookUser("1231375848120ddx")
    println(user2.nickname)
}
*/

// 활용 2. Companion Object는 상속 이나 인터페이스를 구현하는 것이 가능합니다.

// Map<String, Any> = JSON
// {
//   "name": "Tom",
//   "age": 42
// }

interface MapFactory<T> {
    fun fromMap(map: Map<String, Any>): T
}

data class Person(val name: String, val age: Int) {
    companion object : MapFactory<Person> {
        override fun fromMap(map: Map<String, Any>): Person {
            val name = map["name"] as String
            val age = map["age"] as Int
            return Person(name, age)
        }
    }
}

data class User(val name: String) {
    companion object : MapFactory<User> {
        override fun fromMap(map: Map<String, Any>): User {
            val name = map["name"] as String
            return User(name)
        }
    }
}


fun <T> loadFromMap(json: Map<String, Any>, factory: MapFactory<T>): T {
    return factory.fromMap(json)
}

class Point {
    companion object {
        var name: String = "Point"
    }
}

fun main() {
    println(Point.name)

    val json = mapOf(
        "name" to "Tom",
        "age" to 42
    )

    val person = loadFromMap(json, Person)
    println(person)

    val user = loadFromMap(json, User)
    println(user)

    // val person = Person.fromMap(json)
    // println(person)
}

/*
    // Reflection
    //  1. 타입 체크
    //  2. 동적 생성 - 런타임에 특정한 타입의 객체를 생성하는 기법
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
*/
















