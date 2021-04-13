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
    val fn: (Int, Int) -> Unit = ::move

    // fn = User::move  // ?
    val fn2: (User, Int, Int) -> Unit = User::move

    val user = User()
    user.move(10, 20) // User::move(user, 10, 20)

    fn2(user, 10, 20)
}