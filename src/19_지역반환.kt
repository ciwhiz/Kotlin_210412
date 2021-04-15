// 19_지역반환
package ex19

class Person(val name: String)

// 1. for-loop
fun lookForAlice1(people: List<Person>) {
    for (person in people) {
        if (person.name == "Alice") {
            println("Found!!")
            // return
            // continue
            break
        }

        println("Not Found!!")
    }

    println("Failed to find Alice")
}

inline fun <T> Iterable<T>.forEach2(action: (T) -> Unit): Unit {
    for (element in this) action(element)
}

// 2. forEach
//  - Lambda Expression
//    : return이 비지역 반환을 수행합니다.
fun lookForAlice2(people: List<Person>) {
    run {
        people.forEach { person ->
            if (person.name == "Alice") {
                println("Found!!")
                // return             // 비지역반환:   for-loop: return
                // return@forEach     // 지역반환     for-loop: continue
                return@run            //            for-loop: break
            }

            println("Not Found!!")
        }
    }

    println("Failed to find Alice")
}

// 3. forEach - anonymous function
/*
fun lookForAlice(people: List<Person>) {
    people.forEach(fun(person: Person) {
        if (person.name == "Alice") {
            println("Found!!")
            // return             // 지역 반환
            return@lookForAlice   // 비지역 반환
        }
    })

    println("Failed to find Alice")
}
*/

fun lookForAlice3(people: List<Person>) {

    people.forEach outer@{
        people.forEach inner@{ person ->
            if (person.name == "Alice") {
                println("Found!!")
                return@outer
            }

            return@inner
        }
    }


    println("Failed to find Alice")
}

// 1)
//     Found!!

// 2)
//     Found!!
//     Failed to find Alice

fun main() {
    val list = listOf(
        Person("Alice"),
        Person("Tom"),
        Person("Bob"),
        Person("Alice")
    )

    lookForAlice1(list)
}