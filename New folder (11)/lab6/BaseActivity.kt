package com.example.lab6

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

open class BaseActivity : ComponentActivity() {

    protected val TAG = "22621613"
    protected var lifecycleState by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            update()
        }

        lifecycle.addObserver(lifecycleObserver())
    }

    @Composable
    open fun update() {
        UIStateScreen(lifecycleState)
    }

    private fun lifecycleObserver() = LifecycleEventObserver { _, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> handleLifecycleChange("onCreate")
            Lifecycle.Event.ON_START -> handleLifecycleChange("onStart")
            Lifecycle.Event.ON_RESUME -> handleLifecycleChange("onResume")
            Lifecycle.Event.ON_PAUSE -> handleLifecycleChange("onPause")
            Lifecycle.Event.ON_STOP -> handleLifecycleChange("onStop")
            Lifecycle.Event.ON_DESTROY -> handleLifecycleChange("onDestroy")
            else -> {}
        }
    }

    protected fun handleLifecycleChange(state: String) {
        lifecycleState = state
        Log.d(TAG, "Lifecycle State: $state")
        Toast.makeText(this, "State: $state", Toast.LENGTH_SHORT).show()
    }

    // Moved here, as requested
    @Composable
    fun UIStateScreen(state: String) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Текущо състояние на Activity-то:")
            Text(text = state)
        }
    }
}
