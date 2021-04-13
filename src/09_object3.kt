// 09_object3.kt
package ex9_3

// 3. Anonymous Object(익명 객체)
interface MouseAdapter {
    fun mouseClicked()
    fun mouseEntered()
}

class Window {
    var adapter: MouseAdapter? = null

    // lateinit var adapter: MouseAdapter
    // 이후에 초기화를 수행하겠다.

    // Smart cast to 'MouseAdapter' is impossible,
    // because 'adapter' is a mutable property that could have been changed by this time
    fun click() {
        // adapter.mouseClicked()

        /*
        if (adapter != null)
            adapter.mouseClicked()
        */

        // 1. 별도의 변수로 할당해서 사용한다.
        /*
        val adapter = adapter
        if (adapter != null)
            adapter.mouseClicked()
        */

        // 2. 강제로 호출한다. - !!
        //  : null일 경우, 런타임 오류가 발생합니다.
        // adapter!!.mouseClicked()

        // 3. lateinit var
        //  : 초기화되지 않은 접근시 런타임 오류가 발생합니다. - UninitializedPropertyAccessException
        // adapter.mouseClicked()

        // 4. Safe Call(?.)
        adapter?.mouseClicked()
    }

    fun enter() {
        adapter?.mouseEntered()
    }
}

fun main() {
    val window = Window()
    var n = 0
    window.adapter = object : MouseAdapter {
        override fun mouseClicked() {
            println("mouseClicked - ${++n}")
        }

        override fun mouseEntered() {
            println("mouseEntered")
        }
    }

    window.click()
    window.click()
    window.click()
    window.enter()
}