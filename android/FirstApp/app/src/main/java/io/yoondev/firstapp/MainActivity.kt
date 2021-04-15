package io.yoondev.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.yoondev.firstapp.databinding.MainAcitivityBinding

//      Project-level: build.gradle
//  Module-level(app): build.gradle

class MainActivity : AppCompatActivity() {


    // activity_main.xml
    // var binding: ActivityMainBinding

    // main_activity.xml
    lateinit var binding: MainAcitivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainAcitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setContentView(R.layout.activity_main)

        // 1. findViewById
        //  Java: ButterKnife
        // val textView = findViewById<TextView>(R.id.textView)
        // textView.text = "Hello"

        // 2. kotlin-android-extensions - Deprecated!
        /*
        textView.text = "Hello"
        button.setOnClickListener {
            textView.text = "OK"
        }
        */

        // 3. View-Binding


    }
}