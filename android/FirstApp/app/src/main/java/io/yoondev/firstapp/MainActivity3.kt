package io.yoondev.firstapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import io.yoondev.firstapp.databinding.MainActivity3Binding
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor


/*
{
  "login": "JakeWharton",
  "id": 66577,
  "avatar_url": "https://avatars.githubusercontent.com/u/66577?v=4",
  "name": "Jake Wharton",
  "company": "Square",
  "email": null,
}
*/

// HTTPRequest / Volley ...
//  => OKHttpClient

// DTO(Data Transfer Object)
//  => null 가능성이 있는 타입은 Nullable로 만들어야 합니다.
//  => gson
//     : "null"
//  => proguard 예외로 등록해야 합니다. - Reflection
//  dto.User
data class User(
    val login: String,
    val id: Int,
    // @field:SerializedName("avatar_url") val avatarUrl: String,
    val avatarUrl: String,
    val name: String?,
    val company: String?,
    val email: String?,
)

// - Java(JVM) vs C#(CLR)
//  C#: const     / readonly
// C++: constexpr / const(compile-time / runtime)

class MainActivity3 : AppCompatActivity() {
    private val binding: MainActivity3Binding by viewBinding()

    // private static final String TAG = "MainActivity3" : 컴파일 타임 상수의 가능성이 있다.
    // private static final String TAG = MainActivity3.class.getSimpleName() : 불가능

    // 상수
    // - 컴파일 타임 상수
    // - 런타임 상수: 메모리가 할당되어 있는 언어적으로 변경이 불가능하다.

    companion object {
        // 컴파일 타임 상수 - const : 메모리 사용 X
        // private const val TAG1 = "MainActivity3"

        // 컴파일 타임 상수 처리가 불가능합니다. : 메모리 사용 O => 런타임 상수
        private val TAG = MainActivity3::class.java.simpleName
    }

    // init {
    //    System.loadLibrary("HelloJni")
    // }

    // JNI
    //   Java:   native void foo()
    // Kotlin: external fun foo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // api.github.com/users/JakeWharton
        binding.loadButton.setOnClickListener {

            // SAM 지원
            /*
            Thread(object: Runnable {
                override fun run() {

                }
            })
            */

            Thread {


                // 1. OKHttpClient 객체 생성
                val client = OkHttpClient.Builder().apply {
                    /*
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
                addInterceptor(loggingInterceptor)
                */

                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    })

                }.build()

                // 2. Request 생성
                //  : url / HTTP method / body / param
                val request = Request.Builder().apply {
                    get()
                    url("https://api.github.com/users/JakeWharton")
                }.build()


                // 3. 'Call' 을 생성합니다.
                //  '동기적' vs '비동기적'
                //  동기: execute()
                // 비동기: enqueue()
                val call = client.newCall(request)

                // 4. 동기적 호출(execute) - Response
                //  : NetworkOnMainThreadException
                val response: Response = call.execute()

                // 5. Response
                //  : HTTP status code
                //   200 ~ 299: OK
                //   400 ~ 499: Client Error!
                //   500 ~ 599: Server Error!


                // if (response.code in 200..299) {
                if (response.isSuccessful) {

                    response.body?.let { body ->
                        val json = body.string()
                        // Log.e(TAG, "Response: $json")

                        val gson = GsonBuilder().apply {
                            setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        }.create()

                        val user = gson.fromJson(json, User::class.java)
                        Log.e(TAG, "Response: $user")

                    }

                }

            }.start()


        }
    }
}





