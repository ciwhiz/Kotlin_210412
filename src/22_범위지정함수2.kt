// 22_범위지정함수2.kt
package ex22

// 코틀린은 Try with resources 문법을 지원하지 않습니다.
class Resource : AutoCloseable {
    override fun close() {
        println("Resource close")
    }

    fun foo() {
        println("Resource foo")
    }
}

// use - AutoClosable
fun main() {
    val res = Resource()
    res.use { e ->
        e.foo()
    }
}