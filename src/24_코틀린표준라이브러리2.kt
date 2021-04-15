// 24_코틀린표준라이브러리2.kt
package ex24

// Iterator Pattern
//  의도: 컬렉션의 내부 구조에 상관없이 요소를 열거할 수 있는 패턴
// - 핵심 인터페이스 2가지
//  1. Iterator
//  2. Iterable

// 컬렉션의 설계자는 자신 컬렉션을 열거할 수 있는 객체를 제공해야 한다. - Iterator
class SListIterator<E>(private var current: SList.Node<E>?) : Iterator<E> {
    override fun hasNext(): Boolean = current != null

    // 현재의 값을 반환하고, 다음 위치로 이동합니다.
    override fun next(): E {
        val ret = current?.value
        current = current?.next

        return ret!!
    }
}

class SList<E> : Iterable<E> {
    override fun iterator(): Iterator<E> = SListIterator(head)

    class Node<E>(val value: E, val next: Node<E>?)

    var head: Node<E>? = null

    val front: E?
        get() = head?.value

    fun addFront(value: E) {
        head = Node(value, head)
    }
}

fun main() {
    val list = SList<Int>()
    list.addFront(10)
    list.addFront(20)
    list.addFront(30)

    println(list.front)

    // 컬렉션의 이용자는 컬렉션이 제공하는 반복자의 구체적인 타입 이름을 알 수 없습니다.
    // => 컬렉션의 설계자는 반드시 반복자를 제공하는 인터페이스를 구현해야 합니다. - Iterable

    // val iterator = SListIterator(list.head)
    val iterator = list.iterator()
    while (iterator.hasNext()) {
        println("${iterator.next()}")
    }

    /*
    val list = listOf(10, 20, 30)

    val iterator = list.iterator()
    while (iterator.hasNext()) {
        println("${iterator.next()}")
    }
    */


    /*
    for (e in list) {
        println(e)
    }
    */


}