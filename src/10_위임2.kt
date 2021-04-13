// 10_위임2
package ex10_2

import kotlin.reflect.KProperty

// 프로퍼티 위임
//  : 프로퍼티에 대한 getter / setter에 대한 호출을 다른 객체에게 위임한다.

/*
class SampleDelegate {
    operator fun getValue(thisRef: User, property: KProperty<*>): String {
        return "Tom"
    }

    operator fun setValue(thisRef: User, property: KProperty<*>, newValue: String) {
        println("setValue - $newValue")
    }
}
*/

// Type 'SampleDelegate' has no method 'getValue(User, KProperty<*>)'
// and thus it cannot serve as a delegate
// Type 'SampleDelegate' has no method 'setValue(User, KProperty<*>, String)'
// and thus it cannot serve as a delegate for var (read-write property)


interface OnValueChanged {
    fun onChangedValue(old: String, new: String)
}

interface Predicate {
    fun test(value: String): Boolean
}

class SampleDelegate(
    var field: String,
    var onValueChanged: OnValueChanged? = null,
    var predicate: Predicate? = null,
) {
    operator fun getValue(thisRef: User, property: KProperty<*>): String {
        return field
    }

    operator fun setValue(thisRef: User, property: KProperty<*>, newValue: String) {
        val oldValue = field

        // val result = predicate?.test(newValue)
        val predicate = predicate
        if (predicate != null && predicate.test(newValue).not()) {
            return
        }

        field = newValue
        onValueChanged?.onChangedValue(oldValue, newValue)
    }
}

class User {
    // Backing Field 가 없는 프로퍼티
    var name: String by SampleDelegate(
        "Alice",
        object : OnValueChanged {
            override fun onChangedValue(old: String, new: String) {
                println("name: $old -> $new")
            }
        },
        object : Predicate {
            override fun test(value: String): Boolean {
                return value.length >= 5
            }
        }
    )
}

fun main() {
    val user = User()
    user.name = "Bob"

    println(user.name)
}