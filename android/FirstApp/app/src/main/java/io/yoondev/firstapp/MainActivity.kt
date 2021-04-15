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
        // 3. View-Binding
        // 1) Naming Convention
        //   - main_activity.xml  => MainActivityBinding

        //---------------------------------
        //   - btn_ok             => btnOk
        //   - tv_name            => tvName
        //---------------------------------
        //   - okButton
        //   - nameTextView


        binding = MainAcitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        binding.okButton.setOnClickListener {
            binding.nameTextView.text = "OK"
        }
        */

        with (binding) {
            okButton.setOnClickListener {
                nameTextView.text = "OK"
            }
        }


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


    }
}