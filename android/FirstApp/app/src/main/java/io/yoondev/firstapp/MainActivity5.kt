package io.yoondev.firstapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.yoondev.firstapp.databinding.MainActivity3Binding


// Reactive eXtension => Rx
//  => 비동기 연산을 컬렉션을 다루는 것처럼 일반적인 연산을 통해 처리할 수 있다.

// Collection 설계 기술
//   Iterator Pattern
//----------------------------
//   Iterable  -  Iterator    : pull
//                   fun hasNext(): Boolean
//                   fun next(): E

//  map / filter / flatMap / distinct ...

//   Observer Pattern(Rx)
//----------------------------
//  Observable -  Observer    : push
//                   fun onNext(E)

// Rx(Reactive eXtension) 구성 요소
// 1. Observable
//   : 이벤트를 발생하는 주체로, 이벤트 스트림을 통해 이벤트를 Observer에게 전달합니다.
//                                                 - onNext(E)
//                                                 - onError(Throwable)

// 2. Observer
//   : Observable에서 발생한 이벤트에 반응하는 객체
//     이벤트가 발생하였을 때 수행할 작업을 정의합니다.

//    "Observer가 Observable을 구독(subscribe)한다"
//    => 이벤트 스트림이 형성된다.
//     -           onNext(E): 이벤트가 발생하였다
//     -  onError(Throwable): 오류가 발생하였을 때
//     -        onComplete(): 이벤트 스트림이 종료되었다.

// Observable <-(subscribe) Observer
//                   onNext(1)
//                   onNext(2)
//                   onComplete()

//                   onNext(1)
//                   onError()


// 3. Operator
//  : 연산자(Operator)는 이벤트 스트림을 통해 전달되는 이벤트에 연산을 수행합니다.

// Observable - map(e * 10) - (subscribe) Observer
//    1                             onNext(10)
//    2                             onNext(20)


// Observable - filter(e % 2 == 1) - (subscribe) Observer
//    1                               onNext(1)
//    2                                   X

// 4.
// 5.




class MainActivity5 : AppCompatActivity() {
    private val binding: MainActivity3Binding by viewBinding()

    companion object {
        private const val TAG1 = "MainActivity5"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}