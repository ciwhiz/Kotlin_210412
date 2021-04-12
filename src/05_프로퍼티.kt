// 05_프로퍼티.kt
package ex5

// 프로퍼티(Property)
// => 접근자 메소드를 자동으로 생성하는 기술
//  var: getter + setter
//  val: getter

// class User(private val name: String, var age: Int)
// - getter / setter 내부에서 필드의 값을 읽어올 수 있는 field 라는 키워드를 사용해야 합니다.

class User {
    val name: String
        get() {
            return "name: $field"
        }

    var age: Int
        get() {
            return field + 10
        }
        set(value) {
            field = value - 1
        }

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}

fun main() {
    val user = User("Tom", 42)

    user.age = 100     // setAge
    println(user.age)  // getAge

    println(user.name)
}
