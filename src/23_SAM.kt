// 23_SAM
package ex23

import com.lge.ex23.OnClickListener


class Button {
    var onClickListener: OnClickListener? = null
}

class Activity {
    val button = Button()

    fun onCreate() {
        button.onClickListener = object : OnClickListener {
            override fun onClick() {
                startActivity(this@Activity)
            }
        }

        // Kotlin - SAM(Single Abstract Method) 지원
        button.onClickListener = OnClickListener {
            startActivity(this)
        }
    }

    fun startActivity(activity: Activity) {
    }

}

fun main() {

}