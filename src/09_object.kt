package ex9

// 09_object.kt
//  1) object 선언(declaration) - 'Singleton'
//  - 오직 한개의 객체를 생성하고, 언제 어디서든 동일한 방법을 통해 접근할 수 있는 객체: Singleton
//  - 생성자를 정의할 수 없습니다.

object Cursor {
    // var name: String = "Cursor"

    var name: String

    init {
        name = "Cursor"
        println("Cursor 객체 초기화 블록")
    }

    fun move(x: Int, y: Int) {
        println("Cursor move($x, $y)")
    }
}


//  2) companion object
//  3) anonymous object

fun main() {
    println("Main 함수")
    Cursor.move(10, 20)
}