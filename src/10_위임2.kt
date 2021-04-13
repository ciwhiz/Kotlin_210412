// 10_위임2
package ex10_2

import kotlin.properties.Delegates
import kotlin.properties.ObservableProperty
import kotlin.properties.ReadWriteProperty
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


interface OnValueChanged<T> {
    fun onChangedValue(old: T, new: T)
}

interface Predicate<T> {
    fun test(value: T): Boolean
}

class SampleDelegate<T>(
    var field: T,
    var onValueChanged: OnValueChanged<T>? = null,
    var predicate: Predicate<T>? = null,
) {
    operator fun getValue(thisRef: User, property: KProperty<*>): T {
        return field
    }

    operator fun setValue(thisRef: User, property: KProperty<*>, newValue: T) {
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

fun <T> foo(initialValue: T): ReadWriteProperty<Any, T> {
    return object : ObservableProperty<T>(initialValue) {
        override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) {}
    }
}


class User {
    // Backing Field 가 없는 프로퍼티
    var name: String by SampleDelegate(
        "Alice",
        object : OnValueChanged<String> {
            override fun onChangedValue(old: String, new: String) {
                println("name: $old -> $new")
            }
        },
        object : Predicate<String> {
            override fun test(value: String): Boolean {
                return value.length >= 5
            }
        }
    )

    var age: Int by SampleDelegate(0,
        object : OnValueChanged<Int> {
            override fun onChangedValue(old: Int, new: Int) {
                println("age: $old -> $new")
            }
        },
        object : Predicate<Int> {
            override fun test(value: Int): Boolean {
                return value >= 1
            }
        })


    var address: String by Delegates.observable("xxx") { _, _, _ ->
    }
    var address2: String by foo("xxx")
}


fun main() {
    val user = User()
    user.name = "Bob"
    user.age = -100

    println(user.name)
}