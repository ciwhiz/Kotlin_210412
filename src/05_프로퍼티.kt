// 05_프로퍼티.kt
package ex5

// 프로퍼티(Property)
//  1) Backing Field가 있는 프로퍼티

// => 접근자 메소드를 자동으로 생성하는 기술
//  var: getter + setter
//  val: getter

// class User(private val name: String, var age: Int)
// - getter / setter 내부에서 필드의 값을 읽어올 수 있는 field 라는 키워드를 사용해야 합니다.
/*
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

    // 의도: 외부에서 값을 읽는 것은 가능하지만, 변경하는 것은 불가능하도록 하고 싶다.
    var address: String = "Suwon"
        private set

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

    // user.address = "XXX"
    println(user.address)
}
*/

//  2) Backing Field가 없는 프로퍼티: 메소드의 가독성을 높이기 위해 사용한다.
class User(var firstName: String, var lastName: String) {

    // Backing Field가 없는 프로퍼티 - 메소드
    var fullName: String
        get() {
            return "$firstName, $lastName"
        }
        set(value) {
            val arr = value.split(", ")
            firstName = arr[0]
            lastName = arr[1]
        }
}

fun main() {
    val user = User("Gildong", "Hong")
    println(user.fullName)

    user.fullName = "Soonshin, Lee"
    println(user.firstName)
    println(user.lastName)
}
















