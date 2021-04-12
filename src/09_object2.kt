// 09_object2.kt
package ex9_2

//  2) companion object(동반 객체)
//  => 코틀린에서는 static 키워드를 제공하지 않습니다.
//     "정적 메소드 / 정적 프로퍼티" 를 구현하기 위해서는 동반 객체를 이용해야 합니다.
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