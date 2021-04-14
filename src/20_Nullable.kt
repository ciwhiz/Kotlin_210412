// 20_Nullable.kt
package ex20

import ex4.add

// Nullable
//  : 'null'을 안전하게 다루는 방법

class User {
    fun sendMail(email: String) {
        println("User sendMail - $email")
    }

    // 함수가 Nullable이 아닌 값을 가지도록 만드는 것이 편리합니다.
    fun sendMail(email: String, address: String) {
    }

    fun sendMail2(email: String?, address: String?) {
        /*
        email?.let { email ->
            address?.let { address ->
                sendMail(email, address)
            }
        }
        */

        /*
        if (email == null || address == null)
            return
        sendMail(email, address)
        */

        // 코틀린 삼항 연산자가 제공되지 않습니다.
        /*
        val email = if (email == null)
            "default@gmail.com"
        else
            email
        */

        // ?: - null일 경우 기본값을 제공할 수 있습니다.
        //      Elvis Operator
        val email1: String = email ?: "default@gmail.com"
        val address1 = address ?: "Seoul"
        sendMail(email1, address1)
    }

}

fun getUser(): User? {
    return User()
}

var email: String? = null
var address: String? = null

/*
fun main() {
    /*
    val email = "hello@gmail.com"
    val user: User? = getUser()

    user?.sendMail(email)
    */


    val user = User()

    // var a: String?
    // var b: String

    // a = b   // ok!
    // b = a   // error!
    // user.sendMail(email)


    /*
    if (email != null) {
        user.sendMail(email)  // Smart Cast!
                              // 전역 프로퍼티 또는 객체의 프로퍼티가 var인 경우 스마트 캐스트가 동작하지 않습니다.
    }
    */

    /*
    val email = email
    if (email != null) {
        user.sendMail(email)
    }
    */

    // let - Safe Call
    //  => email이 null이 아니면, let 블록을 실행하겠다.
    email?.let { email ->
        user.sendMail(email)
    }

    // let은 중첩될 경우 코드의 가독성이 떨어집니다.
    email?.let { email ->
        address?.let { address ->
            user.sendMail(email, address)
        }
    }

    val email = email
    val address = address
    if (email != null && address != null) {
        user.sendMail(email, address)
    }
}
*/

/*
public inline fun <T, R> T.let(block: (T) -> R): R {
    return block(this)
}
*/

class Country(val name: String, val city: City?)
class City(val name: String, val address: Address?)
class Address(val name: String)

fun main() {
    val country: Country? = Country("KR", City("Seoul", Address("Gangnam")))
    if (country != null) {
        if (country.city != null) {
            if (country.city.address != null) {
                println(country.city.address.name)
            }
        }
    }

    country?.city?.address?.name?.let { name ->
        println(name)
    }

}





