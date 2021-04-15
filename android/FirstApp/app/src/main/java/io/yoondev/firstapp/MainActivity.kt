package io.yoondev.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import io.yoondev.firstapp.databinding.ListFragmentBinding
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

        /*
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFrame, MainFragment())
            .commitNow()
        */

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFrame, ListFragment())
            .commitNow()
    }
}


class MainFragment : Fragment() {


    // Version 1.
    /*
    private var binding: MainFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = binding ?: return
        binding.nameTextView.text = "Hello"
        binding.okButton.setOnClickListener {
            binding.nameTextView.text = "OK"
        }
    }
    */

    private var _binding: MainFragmentBinding? = null

    private val binding: MainFragmentBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nameTextView.text = "Hello"
        binding.okButton.setOnClickListener {
            binding.nameTextView.text = "OK"
        }
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




class ListFragment : Fragment() {
    private var _binding: ListFragmentBinding? = null

    private val binding: ListFragmentBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}

// View-Binding X
private class ListAdapter : RecyclerView.Adapter<ListAdapter.Holder>() {

    val items = emptyList<String>()

    class Holder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item, parent, false)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(parent)

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: Holder, position: Int) {

        with(holder.itemView) {
            val nameTextView = findViewById<TextView>(R.id.nameTextView)
            val commitButton = findViewById<Button>(R.id.commitButton)

            nameTextView.text = items[position]
            commitButton.setOnClickListener {
                Toast.makeText(context, items[position], Toast.LENGTH_SHORT).show()
            }
        }

        /*
        val nameTextView = holder.itemView.findViewById<TextView>(R.id.nameTextView)
        val commitButton = holder.itemView.findViewById<Button>(R.id.commitButton)

        nameTextView.text = items[position]
        commitButton.setOnClickListener {
            Toast.makeText(holder.itemView.context, items[position], Toast.LENGTH_SHORT).show()
        }
        */
    }


}










