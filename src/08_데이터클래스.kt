// 08_데이터클래스.kt

package ex8

import java.util.*

// - VO(Value Object)
// - DAO(Database Access Object)
// - DTO(Data Transfer Object)


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

    // 연산자 재정의(Operator Overloading)
    // : 연산자를 사용하였을 경우, 약속된 메소드 또는 함수가 호출된다.
    // - 비구조화 선언 문법
    operator fun component1(): String {
        return name
    }

    operator fun component2(): Int {
        return age
    }
}


// data class User(val name: String, val age: Int)
// 반드시 한개 이상의 프로퍼티가 존재해야 합니다.
// data class Person(val name: String)

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
    if (user2 === user3) {
        println("Same Ref")
    } else {
        println("Diff Ref")
    }

    // 4. 내부의 프로퍼티 중에서 변경을 수행할 수 있습니다.
    // val user4 = user.copy(name = "Bob")  // name은 다른 값으로 변경됩니다.
    // val user5 = user.copy(age = 100)

    println(user3)

    // 5. 비구조화 선언
    // arr = new ArrayList<User>()
    // arr.add(...)

    val users = listOf(user, user2)
    for (e in users) {
        println("name=${e.name}, age=${e.age}")
    }

    // val (name, age) = user5
    // println("name=$name, age=$age")

    for ((name, age) in users) {
        println("name=$name, age=$age")
    }

}