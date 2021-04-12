// 08_데이터클래스.kt

package ex8

import java.util.*

// - VO(Value Object)
// - DAO(Database Access Object)
// - DTO(Data Transfer ObjecT)

class User(val name: String, val age: Int) {
    override fun toString(): String {
        return "User(name=$name, age=$age)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (name != other.name) return false
        if (age != other.age) return false

        return true
    }

    override fun hashCode(): Int {
        return Objects.hash(name, age)
    }
}

fun main() {
    val user = User("Tom", 42)
    val user2 = User("Tom", 42)

    // 1. 객체를 문자열로 표현하는 방법 - toString()
    println(user)

    // 2. 객체 동등성 판단 (==) - hashCode / equals
    if (user == user2) {
        println("Same")
    } else {
        println("Diff")
    }

    // 3. 객체 복제


}