package io.yoondev.firstapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import io.yoondev.firstapp.databinding.*
import kotlin.properties.Delegates
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


// Property Delegate
class ActivityBindingDelegate<T : ViewBinding>(
    private val bindingClass: Class<T>,
    val activity: Activity
) : ReadOnlyProperty<Activity, T> {

    private var binding: T? = null

    override fun getValue(thisRef: Activity, property: KProperty<*>): T {
        binding?.let {
            return it
        }

        // inflate(LayoutInflater)
        val inflateMethod = bindingClass.getMethod(
            "inflate",
            LayoutInflater::class.java
        )

        @Suppress("UNCHECKED_CAST")
        binding = inflateMethod.invoke(null, thisRef.layoutInflater) as T
        thisRef.setContentView(binding!!.root)

        return binding!!
    }
}

inline fun <reified T : ViewBinding> Activity.viewBinding() =
    ActivityBindingDelegate(T::class.java, this)


class MainActivity2 : AppCompatActivity() {

    private val binding: MainActivityBinding by viewBinding()

    /*
    val binding: MainActivityBinding by ActivityBindingDelegate(
        MainActivityBinding::class.java,
        this
    )
    */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.nameTextView.text = "hello"
        binding.okButton.setOnClickListener {
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
        }

    }
}

class MainFragment2 : Fragment() {
    
}




//-------
// https://github.com/airbnb/lottie-android/blob/master/sample/src/main/kotlin/com/airbnb/lottie/samples/utils/FragmentViewBindingDelegate.kt
inline fun <reified T : ViewBinding> Fragment.viewBinding() =
    FragmentViewBindingDelegate(T::class.java, this)

class FragmentViewBindingDelegate<T : ViewBinding>(
    bindingClass: Class<T>,
    val fragment: Fragment
) : ReadOnlyProperty<Fragment, T> {
    private val clearBindingHandler by lazy(LazyThreadSafetyMode.NONE) { Handler(Looper.getMainLooper()) }
    private var binding: T? = null

    private val bindMethod = bindingClass.getMethod("bind", View::class.java)

    init {
        fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
            viewLifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun onDestroy() {
                    // Lifecycle listeners are called before onDestroyView in a Fragment.
                    // However, we want views to be able to use bindings in onDestroyView
                    // to do cleanup so we clear the reference one frame later.
                    clearBindingHandler.post { binding = null }
                }
            })
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        binding?.let { return it }

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            error("Cannot access view bindings. View lifecycle is ${lifecycle.currentState}!")
        }

        binding = bindMethod.invoke(null, thisRef.requireView()) as T
        return binding!!
    }
}


// https://github.com/airbnb/lottie-android/blob/master/sample/src/main/kotlin/com/airbnb/lottie/samples/utils/ViewViewBindingDelegate.kt
inline fun <reified T : ViewBinding> ViewGroup.viewBinding() =
    ViewBindingDelegate(T::class.java, this)

class ViewBindingDelegate<T : ViewBinding>(
    private val bindingClass: Class<T>,
    val view: ViewGroup
) : ReadOnlyProperty<ViewGroup, T> {
    private var binding: T? = null

    override fun getValue(thisRef: ViewGroup, property: KProperty<*>): T {
        binding?.let { return it }

        @Suppress("UNCHECKED_CAST")
        binding = try {
            val inflateMethod =
                bindingClass.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java)
            inflateMethod.invoke(null, LayoutInflater.from(thisRef.context), thisRef)
        } catch (e: NoSuchMethodException) {
            val inflateMethod = bindingClass.getMethod(
                "inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.java
            )
            inflateMethod.invoke(null, LayoutInflater.from(thisRef.context), thisRef, true) as T
        } as T
        return binding!!
    }
}


