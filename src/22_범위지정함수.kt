// 22_범위지정함수.kt
package ex22

import java.lang.StringBuilder

// 1. let - Nullable
// 2. with / apply

/*
fun alphabet(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }
    return result.toString()
}
*/

// 수신 객체(this) 지정 람다
//  T.() -> R
inline fun <T, R> with1(receiver: T, block: T.() -> R): R {
    // return block(receiver)
    return receiver.block()
}

// with
fun alphabet_with(): String = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    return toString()
}

inline fun <T> T.apply1(block: T.() -> Unit): T {
    block()
    return this
}

// apply - Builder 생성 로직에서 많이 사용됩니다.
fun alphabet_apply(): String = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
}.toString()

// buildString
fun alphabet(): String = buildString {
    for (letter in 'A'..'Z') {
        append(letter)
    }
}


//fun alphabet(): String = with(StringBuilder()) {
//    for (letter in 'A'..'Z') {
//        append(letter)
//    }
//    return toString()
//}

interface OnClickListener {
    fun onClick()
}

class TextView {
    var text: String? = null
}

class Button {
    var onClickListener: OnClickListener? = null
}

class ViewHolder {
    var view = View()
}

class View {
    val nameTextView: TextView = TextView()
    val emailTextView: TextView = TextView()
    val loginButton: Button = Button()
}

class User {
    fun setName(name: String) {

    }

    fun setAge(age: Int) {

    }
}

fun main() {
    val user1 = User()
    user1.setName("Tom")
    user1.setAge(42)

    val user2 = User().apply {
        setName("Tom")
        setAge(42)
    }

    val user3 = User().also { user ->
        user.setName("Tom")
        user.setAge(42)
    }


    val holder = ViewHolder()
    holder.view.nameTextView.text = "Tom"
    holder.view.emailTextView.text = "hello@gmail.com"
    holder.view.loginButton.onClickListener = object : OnClickListener {
        override fun onClick() {
            // ...
        }
    }

    with(holder.view) {
        nameTextView.text = "Tom"
        emailTextView.text = "hello@gmail.com"
        loginButton.onClickListener = object : OnClickListener {
            override fun onClick() {
                // ...
            }
        }
    }


    val result = alphabet()
    println(result)
}