// 03_클래스문법.kt
package ex3

class Car {
    /*
    fun go() {
        println("go")
    }

    fun go(speed: Int) {
        println("go - speed: $speed")
    }

    fun go(speed: Int, color: Int) {
        println("go - speed: $speed, color: $color")
    }

    fun go(speed: Int, color: Int, destination: String) {
        println("go - speed: $speed, color: $color destination: $destination")
    }
    */

    // 파라미터 기본값을 이용해서, 오버로딩과 같은 효과를 가질 수 있습니다.
    fun go(speed: Int = 60, color: Int = 0xff0000, destination: String = "Seoul") {
        println("go - speed: $speed, color: $color destination: $destination")
    }
}

fun main() {
    val car = Car()
    val a = 100
    val b = 255
    val c = "Suwon"

    // 값 인자가 어떤 파라미터로 전달되는지 알기 어렵다.
    car.go()
    car.go(a)
    car.go(a, b)
    car.go(a, b, c)

    // 파라미터 라벨 지정해서 호출할 수 있습니다.
    car.go(speed = a)
    car.go(speed = a, color = b)
    car.go(speed = a, color = b, destination = c)

    // 순서를 바꿔서 호출하는 것도 가능합니다. - 권장 X
    car.go(color = b, destination = c, speed = a)

    // Kotlin 1.4
    // - 부분 파라미터 지정이 가능합니다.
    // - 순서가 중요합니다.
    car.go(a, b, destination = c)
    car.go(a, color = b, c)
    // car.go(color = b, a, c)  // error!

    // 1.4 부터는 Trailing Comma가 지원됩니다.
    car.go(
        color = b,
        destination = c,
        speed = a,
    )
}







