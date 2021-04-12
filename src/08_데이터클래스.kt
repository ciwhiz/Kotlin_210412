// 08_데이터클래스.kt

package ex8

import java.util.*

// - VO(Value Object)
// - DAO(Database Access Object)
// - DTO(Data Transfer Object)

/*
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

    /*
    constructor(other: User) {

    }
    */
    fun copy(): User {
        return User(name, age)
    }

    // Kotlin에서는 "clone/finalize" 더 이상 정의할 수 없습니다.
    // "finalize" 문제점
    // 1. 정확한 호출 시점을 알 수 없다.
    // 2. 자식 클래스가 부모의 finalize를 명시적으로 호출하지 않으면, 호출되지 않는다.
    // 3. 호출되는 것이 보장되지 않는다.
}
*/

data class User(val name: String, val age: Int)

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
    val user3 = user.copy()
    println(user3)
}