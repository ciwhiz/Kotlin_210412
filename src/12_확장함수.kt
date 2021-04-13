// 12_확장함수
package ex12

// (Int, Int) -> Unit
fun move(x: Int, y: Int) {
    println("Global move")
}

class User {

    // fun move(this: User, x: Int, y: Int)
    fun move(x: Int, y: Int) {
        println("User move - $this")
    }
}

fun main() {
    var fn: (Int, Int) -> Unit = ::move

    // fn = User::move  // ?
    val fn2: (User, Int, Int) -> Unit = User::move

    fn(10, 20)
    val user = User()
    fn = user::move   // Bound reference - this의 객체가 이미 bind 되었다.
    fn(10, 20)

    user.move(10, 20) // User::move(user, 10, 20)
    fn2(user, 10, 20)
}