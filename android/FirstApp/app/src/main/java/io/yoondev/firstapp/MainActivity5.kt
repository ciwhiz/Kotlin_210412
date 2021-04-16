package io.yoondev.firstapp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.yoondev.firstapp.databinding.MainActivity3Binding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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

// 4. Scheduler
//   : 작업을 수행할 스레드를 제어할 수 있습니다.
//   - observeOn: 작업을 수행할 스레드를 지정합니다.
//   - subscribeOn: Observable의 로직이 수행되는 컨텍스트(스레드)를 지정합니다.

// 5. Disposable
//   : Observer가 Observable을 구독할 때 '이벤트 스트림'이 생성됩니다.
//     이벤트 스트림 자원입니다. => 명시적인 종료가 필요합니다(dispose)

interface GithubApi {

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Call<User>

    @GET("search/users")
    fun searchUser(
        @Query("q") q: String,
        @Query("page") page: Int = 1,
        @Query("per_page") per_page: Int = 5
    ): Call<UserSearchResult>


    @GET("users/{login}")
    fun getUserRx(@Path("login") login: String): Observable<User>
    // Observable<List<User>>

}

private val httpClient = OkHttpClient.Builder().apply {
    addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    })

}.build()


private val retrofit: Retrofit = Retrofit.Builder().apply {

    baseUrl("https://api.github.com/")
    client(httpClient)

    val gson = GsonBuilder().apply {
        setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    }.create()

    addConverterFactory(GsonConverterFactory.create(gson))

    // Call Adapter
    //  Call<T>  -> Observable<T>
    addCallAdapterFactory(RxJava3CallAdapterFactory.create())

}.build()


val githubApi: GithubApi = retrofit.create(GithubApi::class.java)


class MainActivity5 : AppCompatActivity() {
    private val binding: MainActivity3Binding by viewBinding()

    companion object {
        private const val TAG = "MainActivity5"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.loadButton.setOnClickListener {

            val observable: Observable<User> = githubApi.getUserRx("JakeWharton")
            /*
            observable.subscribe(
                // onNext
                { user ->
                    Log.e("TAG", "onNext: $user")
                },
                // onError
                { t ->
                    Log.e("TAG", "onError: ${t.localizedMessage}")
                },
                // onComplete
                {
                    Log.e("TAG", "onComplete")
                }
            )
            */
            // RxKotlin
            /*
            observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = { user ->
                        Log.e("TAG", "onNext: $user")

                        updateUi(user)
                    },
                    onError = { t ->
                        Log.e("TAG", "onError: ${t.localizedMessage}")
                    },
                    onComplete = {
                        Log.e("TAG", "onComplete")
                    }
                )
           */

            observable
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = { user ->
                        Log.e("TAG", "onNext: $user")

                        updateUi(user)
                    },
                    // onError가 정의되지 않은 경우 - OnErrorNotImplementedException
                    // onError = { t ->
                    //    Log.e("TAG", "onError: ${t.localizedMessage}")
                    // }
                    onError = this::ignoreError  // Bound reference
                )


        }


    }

    fun updateUi(user: User) {
        binding.loginTextView.text = user.login
        binding.nameTextView.text = user.name
        binding.avatarImageView.load(user.avatarUrl) {
            crossfade(enable = true)
            transformations(
                CircleCropTransformation(),
                GrayscaleTransformation(),
            )
        }
    }

    /*
    fun ignoreError(t: Throwable) {

    }
    */
}

fun Activity.ignoreError(t: Throwable) {}




















