// 09_object2.kt
package ex9_2

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

fun main() {
    val user1 = User.newSubscribingUser("hello@gmail.com")
    println(user1.nickname)

    val user2 = User.newFacebookUser("1231375848120ddx")
    println(user2.nickname)
}














