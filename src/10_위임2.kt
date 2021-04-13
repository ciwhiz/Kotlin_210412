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
        // val result = predicate?.test(newValue)
        val predicate = predicate
        if (predicate != null && predicate.test(newValue).not()) {
            return
        }

        val oldValue = field
        field = newValue
        onValueChanged?.onChangedValue(oldValue, newValue)
    }
}

/*
fun <T> observable(initialValue: T, onChange: (property: KProperty<*>, oldValue: T, newValue: T) -> Unit): ReadWriteProperty<Any?, T> {
    return object : ObservableProperty<T>(initialValue) {
        override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) = onChange(property, oldValue, newValue)
    }
}
*/

/*
fun <T> observable(initialValue: T, onChange: (oldValue: T, newValue: T) -> Unit): SampleDelegate<T> {
    return SampleDelegate(initialValue, object : OnValueChanged<T> {
        override fun onChangedValue(old: T, new: T) = onChange(old, new)
    }, null)
}
*/

/*
fun <T> observable(
    initialValue: T,
    onChange: (oldValue: T, newValue: T) -> Unit
): SampleDelegate<T> = SampleDelegate(initialValue, object : OnValueChanged<T> {
    override fun onChangedValue(old: T, new: T) = onChange(old, new)
}, null)
*/

fun <T> observable(
    initialValue: T,
    onChange: (oldValue: T, newValue: T) -> Unit,
    predicate: (T) -> Boolean,
): SampleDelegate<T> = SampleDelegate(initialValue,
    object : OnValueChanged<T> {
        override fun onChangedValue(old: T, new: T) = onChange(old, new)
    },
    object : Predicate<T> {
        override fun test(value: T): Boolean = predicate(value)
    }
)


class User {
    /*
    var name: String by observable("Alice") { old, new ->
        println("name: $old -> $new")
    }
    */
    // 함수가 두 개 이상의 함수를 전달 받을 경우, 파라미터 라벨 지정 방식 호출이 좋습니다.
    var name: String by observable("Alice",
        onChange = { old, new ->
            println("name: $old -> $new")
        },
        predicate = { value ->
            value.length >= 5
        })


    // Backing Field 가 없는 프로퍼티
    /*
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
    */

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


fun <T> foo(initialValue: T): ReadWriteProperty<Any, T> {
    return object : ObservableProperty<T>(initialValue) {
        override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) {}
    }
}


fun main() {
    val user = User()
    user.name = "Bob"
    user.age = -100

    println(user.name)
}