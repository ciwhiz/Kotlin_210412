// 18_inline.kt
package ex18

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

// Kotlin은 synchronized 키워드가 제공되지 않습니다.
//  => 명시적인 락을 이용해서 동기화를 직접 수행해주어야 합니다.

/*
class IncThread : Thread() {
    companion object {
        var n: Int = 0
    }

    override fun run() {
        for (i in 1..1_000_000) {
            n += 1
        }
    }
}

fun main() {
    val t1 = IncThread()
    val t2 = IncThread()

    t1.start()
    t2.start()

    t1.join()
    t2.join()

    println(IncThread.n)
}
*/

/*
fun <T> withLock(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    } finally {
        lock.unlock()
    }
}
*/
fun <T> Lock.withLock(action: () -> T): T {
    lock()
    try {
        return action()
    } finally {
        unlock()
    }
}

class IncThread(val lock: Lock) : Thread() {
    companion object {
        var n: Int = 0
    }

    override fun run() {
        for (i in 1..1_000_000) {
            lock.withLock {
                n += 1
            }
        }
    }

    /*
    override fun run() {
        for (i in 1..1_000_000) {
            withLock(lock) {
                n += 1
            }
        }
    }
    */

    /*
    override fun run() {
        for (i in 1..1_000_000) {
            try {
                lock.lock()
                n += 1
            } finally {
                lock.unlock()
            }
        }
    }
    */


    // 임계 영역 안에서 예외가 발생하면, 데드락의 위험성이 있습니다.
    /*
    override fun run() {
        for (i in 1..1_000_000) {
            lock.lock()
            n += 1
            lock.unlock()
        }
    }
    */
}

fun main() {
    var n = {
        "hello"
    }
    println(n)


    val lock = ReentrantLock()

    val t1 = IncThread(lock)
    val t2 = IncThread(lock)

    t1.start()
    t2.start()

    t1.join()
    t2.join()

    println(IncThread.n)
}