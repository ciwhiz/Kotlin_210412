// 18_inline2.kt
package ex18_2

// Expected performance impact from inlining is insignificant
// Inlining works best for functions with parameters of functional types
// fun add(a: Int, b: Int) = a + b

fun <T> print(element: T) {
    println(element)
}

// 타입 소거 방식
/*
fun print(element: Any) {
    println(element)
}
*/

// 코드 생성 방식
/*
fun print(element: Int) {
    println(element)
}
fun print(element: Double) {
    println(element)
}
fun print(element: String) {
    println(element)
}
*/

/*
// Java
   List<String> list = new ArrayList<String>();
   list.add("hello");
   list.add("world");
   list.add(42);        // compile error!

// ByteCode
   List list = new ArrayList();
   list.add("hello");
   list.add("world");
*/

/*
fun main() {
    // print(42)      // T = Int
    print(3.14)    // T = Double
    print("Hello") // T = String
}
*/


// Generic - 타입에 일반적인 알고리즘을 가지는 함수와 클래스를 만들 수 있습니다
// : 구현 방식
// 1. C++/C#/Swift: 코드 생성 방식
//   장점 - 코드를 생성하는 기술을 이용해 다양한 설계적인 활용이 가능합니다.
//         CRTP, Template Meta Programming, Policy Based Design
//   단점 - 코드 메모리 사용량이 증가한다.

// 2. Java/Kotlin: 타입 소거 방식
//   장점 - 코드 메모리 사용량이 증가하지 않는다.
//   단점 - 타입 체크 목적으로만 사용할 수 있습니다.
//   = 코드를 생성하지 않습니다.
//     Generic 타입을 타입 체크 목적으로 사용합니다.


// Intent = Intent(this, MainActivity.class)
// startActivity(intent)

open class Activity
class MainActivity : Activity()
class Intent(context: Any?, clazz: Class<out Activity>)

fun startActivity(intent: Intent) {}

inline fun <reified T : Activity> startActivity() {
    // 바이트 코드 안에서 T의 타입 정보를 알 수 없다.
    val intent = Intent(null, T::class.java)
    startActivity(intent)
}

fun main() {
    // ArrayList()

    // Class를 기반으로 새로운 객체를 생성하고 싶다.
    val intent = Intent(null, MainActivity::class.java)
    startActivity(intent)

    startActivity<MainActivity>()
}







