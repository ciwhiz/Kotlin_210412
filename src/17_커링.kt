package ex17

// 커링(Currying)
//  : 다중 인수를 갖는 함수를 단일 인수를 갖는 함수들의 함수열로 바꾸는 작업
// 용도
// - 함수의 인자에 대해서 값을 고정하고 싶다. - bind
// - 함수 호출 시점을 지연할 수 있다.

// 일반 버전
// sum(10, 20, 30, 40)
// => 110

// 커링 버전
// sum(10)(20)(30)(40)
// => 110

/*
fun sum(x: Int, y: Int, z: Int) = x + y + z

// Scala
fun sum(x: Int)(y: Int)(z: Int) = x + y + z
*/

/*
fun sum2(x: Int, y: Int): Int = x + y

fun sum2(x: Int): (y: Int) -> Int = { y: Int ->
    x + y
}

fun main() {
    // var result = sum2(10, 20)
    // println(result)

    val result = sum2(10)(20)
    println(result)

    val sum10 = sum2(10)

    println(sum10(20))  // 30
    println(sum10(30))  // 40
}
*/

/*
fun sum3(x: Int, y: Int, z: Int): Int = x + y + z

fun sum3(x: Int): (y: Int) -> (z: Int) -> Int = { y: Int ->
    { z ->
        x + y + z
    }
}

fun main() {
    // println(sum3(10, 20, 30))
    // val result = sum3(10)(20)(30)

    val sum_10_20 = sum3(10)(20)

    println(sum_10_20(40)) // 70
    println(sum_10_20(10)) // 40
}
*/

fun sum2(x: Int, y: Int): Int = x + y
// 커링 버전의 함수를 생성하는 함수를 만들어봅시다.

fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R = { p1 ->
    { p2 ->
        this(p1, p2)
    }
}

fun main() {
    val csum = ::sum2.curried()
    val result = csum(10)(20)
    println(result)
}



