package io.yoondev.firstapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.yoondev.firstapp.databinding.MainActivity3Binding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// Retrofit
//  : OKHttpClient를 이용할 때 보일러플레이트를 효과적으로 제거할 수 있습니다.
//   1) Request 생성
//   2) ResponseBody -> JSON -> gson -> User
//      : converter
//   3) runOnUiThread


// 1. API Interface 정의
// https://api.github.com/users/JakeWharton

interface GithubApi {

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Call<User>
}

//-----
// 2. OKHttpClient 객체 생성
private val httpClient = OkHttpClient.Builder().apply {

    addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    })

}.build()

// 3. Retrofit 객체 생성
private val retrofit: Retrofit = Retrofit.Builder().apply {

    baseUrl("https://api.github.com/")
    client(httpClient)

    val gson = GsonBuilder().apply {
        setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    }.create()

    addConverterFactory(GsonConverterFactory.create(gson))

}.build()

// 4. GithubApi 객체를 생성합니다.
val githubApi: GithubApi = retrofit.create(GithubApi::class.java)
//------

class MainActivity4 : AppCompatActivity() {
    private val binding: MainActivity3Binding by viewBinding()

    companion object {
        private const val TAG1 = "MainActivity4"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loadButton.setOnClickListener {

        }
    }
}


