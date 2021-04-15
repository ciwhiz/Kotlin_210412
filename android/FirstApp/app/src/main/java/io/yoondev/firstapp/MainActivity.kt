package io.yoondev.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.yoondev.firstapp.databinding.MainActivity2Binding
import io.yoondev.firstapp.databinding.MainFragmentBinding

//      Project-level: build.gradle
//  Module-level(app): build.gradle

class MainActivity : AppCompatActivity() {

    // activity_main.xml
    // var binding: ActivityMainBinding

    // main_activity.xml
    // lateinit var binding: MainActivityBinding


    lateinit var binding: MainActivity2Binding

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


        binding = MainActivity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        binding.okButton.setOnClickListener {
            binding.nameTextView.text = "OK"
        }
        */

        /*
        with(binding) {
            okButton.setOnClickListener {
                nameTextView.text = "OK"
            }
        }
        */


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

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFrame, MainFragment())
            .commitNow()
    }
}


class MainFragment : Fragment() {
    private var binding: MainFragmentBinding? = null
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }
}


// Fragment
/*
class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }
}
*/
















