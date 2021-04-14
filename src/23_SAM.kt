// 23_SAM
package ex23

// import com.lge.ex23.OnClickListener

// Kotlin - 1.4
//  : 코틀린의 인터페이스가 하나의 메소드를 가진 경우에도 SAM 지원을 사용할 수 있습니다.
//   "fun interface"
fun interface OnClickListener {
    fun onClick()
}

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