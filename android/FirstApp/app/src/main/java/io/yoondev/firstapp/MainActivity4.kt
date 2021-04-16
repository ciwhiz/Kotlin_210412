package io.yoondev.firstapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.yoondev.firstapp.databinding.MainActivity3Binding

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



