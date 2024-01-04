package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG,"Starting Coroutine in thread ${Thread.currentThread().name}")
            val ans = doNetworkCall()
            withContext(Dispatchers.Main) {
                Log.d(TAG,"Starting Coroutine in thread ${Thread.currentThread().name}")
                binding.tvDummy.text = "Hello Kaustubh"
            }
            Log.d(TAG, ans)
        }
    }

    suspend fun doNetworkCall(): String {
        delay(3000L)
        return "This is the answer"
    }
}