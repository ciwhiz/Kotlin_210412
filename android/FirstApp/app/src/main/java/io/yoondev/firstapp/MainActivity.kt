package io.yoondev.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

//      Project-level: build.gradle
//  Module-level(app): build.gradle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. findViewById
        //  Java: ButterKnife
        // val textView = findViewById<TextView>(R.id.textView)
        // textView.text = "Hello"

        // 2. kotlin-android-extension - Deprecated!
        textView.text = "Hello"
        button.setOnClickListener {
            textView.text = "OK"
        }

        // 3. View-Binding


    }
}