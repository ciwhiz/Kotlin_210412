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
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.plusAssign
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
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.TimeUnit

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


// RxBinding
//  => UI의 비동기 이벤트를 Observable를 통해서 다룰 수 있습니다.
//    implementation 'com.jakewharton.rxbinding4:rxbinding:4.0.0'
//    implementation 'com.jakewharton.rxbinding4:rxbinding-material:4.0.0'

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

    @GET("search/users")
    fun searchUserRx(
        @Query("q") q: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 5
    ): Observable<UserSearchResult>

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

    /*
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

            /*
            observable
                .observeOn(AndroidSchedulers.mainThread())
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
            */

            /*
            // shuffled / first
            githubApi.searchUserRx("hello", perPage = 100)
                .map { result ->
                    result.items.shuffled()
                }
                /*
                .mapOptional {
                    Optional.ofNullable(it.firstOrNull())
                }
                */
                .filter {
                    it.isNotEmpty()
                }
                .map {
                    it.first()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = { user ->
                        updateUi(user)
                    },
                    onError = this::ignoreError
                )
             */


            githubApi.searchUserRx("hello", perPage = 100)
                // Observable<UserSearchResult> -> map -> Observable<List<User>>
                .map {
                    it.items.shuffled()
                }
                .filter {
                    it.isNotEmpty()
                }
                // Observable<List<User>> -> map -> Observable<User>
                .map {
                    it.first()
                }
                // Observable<User> -> map     -> Observable<Observable<User>>
                // Observable<User> -> flatMap -> Observable<User>
                .flatMap {
                    githubApi.getUserRx(it.login)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = { user ->
                        updateUi(user)
                        toast(user.location ?: "Unknown")
                    },
                    onError = this::ignoreError
                )


        }
    }

    */

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


    // var disposable1: Disposable? = null
    // var disposable2: Disposable? = null

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*
        binding.loadButton.setOnClickListener {
            Log.e("XXX", "onClick")
        }
        */

        // UI 같이 절대 종료되지 않는 Observable에 대한 이벤트 스트림은 반드시 명시적인 해지가 필요합니다.
        /*
        val disposable1 = binding.loadButton.clicks()
            .subscribeBy(
                onNext = {
                    Log.e("XXX", "onNext: onClick")
                },
                onError = this::ignoreError,
                onComplete = {
                    Log.e("XXX", "onComplete")
                }
            )


        val disposable2 = binding.loadButton.clicks()
            .subscribeBy(
                onNext = {
                    Log.e("XXX", "onNext: onClick")
                },
                onError = this::ignoreError,
                onComplete = {
                    Log.e("XXX", "onComplete")
                }
            )

        compositeDisposable.add(disposable1)
        compositeDisposable.add(disposable2)
      */

        /*
        compositeDisposable += binding.loadButton.clicks()
            .subscribeBy(
                onNext = {
                    Log.e("XXX", "onNext: onClick")
                },
                onError = this::ignoreError,
            )
        */

        // Observable이 동작하는 스레드 컨텍스트에서 수행된다.

        // LocalDateTime - minimum SDK 26
        /*
        compositeDisposable += binding.loadButton.clicks()
            .throttleFirst(3, TimeUnit.SECONDS)
            .subscribeBy(
                onNext = {
                    Log.e("XXX", "touched - ${Date()}")
                },
                onError = this::ignoreError,
            )
        */

        compositeDisposable += binding.loadButton.clicks()   // Observable<Unit>
            .throttleFirst(3, TimeUnit.SECONDS)
            .flatMap {
                githubApi.searchUserRx("hello", perPage = 100)
            }
            .map {
                it.items.shuffled()
            }
            .filter {
                it.isNotEmpty()
            }
            .map {
                it.first()
            }
            .flatMap {
                githubApi.getUserRx(it.login)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    updateUi(it)
                    toast(it.location ?: "Unknown")
                },
                onError = this::ignoreError
            )


    }

    override fun onDestroy() {
        // disposable1?.dispose()
        // disposable2?.dispose()

        // compositeDisposable.clear()

        compositeDisposable.dispose()

        super.onDestroy()
    }


    /*
    fun ignoreError(t: Throwable) {

    }
    */
}

fun Activity.ignoreError(t: Throwable) {}




















