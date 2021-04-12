// 07_봉인된클래스
package ex07

// 1. enum class
/*
enum class Color {
    RED, ORANGE, YELLOW, GREEN
}
*/

// 2. enum은 객체이기 때문에, 프로퍼티와 메소드를 가질 수 있습니다.
enum class Color(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    YELLOW(255, 165, 0),
    ORANGE(255, 165, 0);
    // 메소드를 정의할 경우 반드시 ; 을 통해 영역 분리가 필요합니다.

    fun rgb(): Int {
        return (r * 255 + g) * 255 + b
    }

    val rgb: Int
        get() {
            return (r * 255 + g) * 255 + b
        }
}

// 3. Java: Switch-Case Statement(문)
//    Kotlin: 제공되지 않습니다.
//            "when expression(식)"

// Statement: 결과값이 존재하지 않습니다.
// Expression: 결과값이 존재합니다.
// => 코틀린은 if도 Expression 입니다.

fun getName(color: Color): String {
    return if (color == Color.RED) {
        "Red"
    } else {
        "Unknown"
    }

    /*
    return when (color) {
        Color.RED -> "Red"
        Color.BLUE -> "Blue"
        Color.GREEN -> "Green"
        Color.ORANGE -> "Orange"
        Color.YELLOW -> "Yellow"
    }
    */

    /*
    when (color) {
        Color.RED -> return "Red"
        Color.BLUE -> return "Blue"
        Color.GREEN -> return "Green"
        Color.ORANGE -> return "Orange"
        Color.YELLOW -> return "Yellow"
    }
    */
}


fun main() {
    val color = Color.RED
    println(color.r)

    println(color.rgb()) // method
    println(color.rgb)   // property
}



