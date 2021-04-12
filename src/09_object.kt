package ex9

import java.io.File

// 09_object.kt
//  1) object 선언(declaration) - 'Singleton'
//  - 오직 한개의 객체를 생성하고, 언제 어디서든 동일한 방법을 통해 접근할 수 있는 객체: Singleton
//  - 생성자를 정의할 수 없습니다.
//  2) companion object
//  3) anonymous object

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

/*
class CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(o1: File, o2: File): Int {
        return o1.path.compareTo(o2.path, ignoreCase = false)
    }
}
*/

// 정책 클래스를 object 선언을 통해 제공하면, 하나의 정책을 공유해서 사용하는 것이 가능합니다.
object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(o1: File, o2: File): Int {
        return o1.path.compareTo(o2.path, ignoreCase = false)
    }
}

data class Person(val name: String) {
    // 중첩 클래스의 형태로 제공하는 것도 가능합니다.
    object NameComparator : Comparator<Person> {
        override fun compare(o1: Person, o2: Person): Int {
            return o1.name.compareTo(o2.name)
        }
    }
}


fun main() {
    val people = listOf(Person("Bob"), Person("Alice"))
    val result2 = people.sortedWith(Person.NameComparator)
    println(result2)

    val files = listOf(File("/z"), File("/A"))
    // - 정책을 사용할 때마다 정책 클래스를 생성해야 합니다.
    // val result = files.sortedWith(CaseInsensitiveFileComparator())
    val result = files.sortedWith(CaseInsensitiveFileComparator)

    println(result)
}


/*
fun main() {
    println("Main 함수")
    Cursor.move(10, 20)
}
*/