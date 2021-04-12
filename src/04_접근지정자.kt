// 04_접근지정자.kt
// Java
//  - private - package - protected - public
//    package: 다른 모듈에서 동일한 패키지를 인위적으로 만들어서 접근이 가능합니다.
//  protected: 같은 패키지내에서 protected 필드나 메소드에 접근이 가능하다.

// Kotlin
//  - private - internal - protected - public
//   internal: 다른 모듈에서 동일한 패키지를 가지더라도 접근이 불가능합니다.
//  protected: 오직 자식 클래스를 통해서만 접근이 가능합니다.

package ex4

class Car {
    protected var name: String = "Car"
}

fun main() {
    val car = Car()
    // car.name = "XXX"
}

