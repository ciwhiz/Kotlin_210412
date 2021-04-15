// 24_코틀린표준라이브러리2.kt
package ex24

class SList {
    class Node(val value: Int, val next: Node?)

    var head: Node? = null

    val front: Int?
        get() = head?.value

    fun addFront(value: Int) {
        head = Node(value, head)
    }
}


fun main() {
    val list = SList()
    list.addFront(10)
    list.addFront(20)
    list.addFront(30)

    println(list.front)
}