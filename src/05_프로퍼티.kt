// 05_프로퍼티.kt
package ex5

// 프로퍼티(Property)
// => 접근자 메소드를 자동으로 생성하는 기술
//  var: getter + setter
//  val: getter

//  1) Backing Field가 있는 프로퍼티

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

//  2) Backing Field가 없는 프로퍼티: 메소드의 '가독성'을 높이기 위해 사용한다.
class User(var firstName: String, var lastName: String) {
    // Backing Field가 없는 프로퍼티 - 메소드
    //  - Swift: 계산형 프로퍼티

    // Initializer is not allowed here because this property has no backing field
    // var fullName: String = "xxx"
    var fullName: String
        get() {
            // firstName = "xxx"
            return "$firstName, $lastName"
        }
        set(value) {
            val arr = value.split(", ")
            firstName = arr[0]
            lastName = arr[1]
        }

    // 아래처럼 다른 타입으로 변환하는 작업을 프로퍼티의 형태로 제공하는 것은 코틀린의 '컨벤션'에 어긋납니다.
    /*
    val description: String
        get() {
            return "User(firstName=$firstName, lastName=$lastName)"
        }
    */
}

fun main() {
    val user = User("Gildong", "Hong")
    println(user.fullName)

    user.fullName = "Soonshin, Lee"
    println(user.firstName)
    println(user.lastName)

    // println(user.description)
}

// 프로퍼티 vs 메소드
// 1. 복잡한 코드 - 메소드
// 2. 시간이 오래 걸리는 작업 - 메소드
// 3. 프로퍼티의 getter 안에서 다른 프로퍼티의 값을 변경하는 행위는 좋지 않습니다.
// 4. 프로퍼티 안에서 예외가 발생하면 안됩니다. - 메소드
// 5. 다른 타입의 값이 필요한 경우 - 메소드
//     : toString()
//       toLong()
//       toPerson()
// 6. 객체의 복사본 - 메소드














