// 07_봉인된클래스
package ex07

import java.lang.Exception
import java.lang.IllegalArgumentException

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
fun mix(c1: Color, c2: Color): Color {
    val s: Set<Color> = setOf(c1, c2)

    return if (s == setOf(Color.RED, Color.YELLOW))
        Color.ORANGE
    else if (s == setOf(Color.BLUE, Color.YELLOW))
        Color.GREEN
    else
        throw Exception("Unknown")

    /*
    return when (s) {
        setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
        setOf(Color.BLUE, Color.YELLOW) -> Color.GREEN
        else -> throw Exception("Unknown")
    }
    */
}


fun getWarmth(color: Color): String {
    return when (color) {
        Color.RED, Color.YELLOW, Color.ORANGE -> "Warmth"
        Color.BLUE -> "Cold"
        else -> "Unknown"
    }
}

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

/*
fun main() {
    val color = Color.RED
    println(color.r)

    println(color.rgb()) // method
    println(color.rgb)   // property
}
*/
//------------------------------------------------

/*
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr


fun eval(e: Expr): Int {
    return when (e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
        else ->
            throw IllegalArgumentException("Unknown expr")
    }
}

fun main() {
    val left = Num(10)
    val right = Num(20)
    val sum = Sum(left, right)
    val sum2 = Sum(left, sum)

    val result = eval(sum2)
    println(result)
}
*/

// 봉인된 클래스(sealed class)
// = 봉인된 인터페이스(sealed interface) - 1.5

// 위의 클래스에 대한 하위 클래스를 같은 파일에서만 만들 수 있습니다.

// 1.4
sealed class Expr
class Num(val value: Int) : Expr()
class Sum(val left: Expr, val right: Expr) : Expr()

/*
// 1.5
// : 봉인된 인터페이스(sealed interface)
sealed interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr
*/

fun eval(e: Expr): Int {
    // 컴파일러는 Expr의 하위 클래스가 더 이상 존재하지 않는 다는 것을 알 수 있습니다.
    return when (e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
    }
}

fun main() {
    val left = Num(10)
    val right = Num(20)
    val sum = Sum(left, right)
    val sum2 = Sum(left, sum)

    val result = eval(sum2)
    println(result)
}















