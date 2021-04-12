// 05_프로퍼티.kt
package ex5

// 프로퍼티(Property)
// => 접근자 메소드를 자동으로 생성하는 기술
//  var: getter + setter
//  val: getter

class User(private val name: String, var age: Int)

fun main() {
    val user = User("Tom", 42)

    user.age = 100     // setAge
    println(user.age)  // getAge

    // println(user.name)
}
