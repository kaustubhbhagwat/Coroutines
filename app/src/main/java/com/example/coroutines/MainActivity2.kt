package com.example.coroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.coroutines.databinding.ActivityMain2Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class MainActivity2 : AppCompatActivity() {

    val TAG = "MainActivity2"
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {

            lifecycleScope.launch {
                while (true){
                    delay(1000L)
                    Log.d(TAG,"Still running")
                }
            }

            GlobalScope.launch {
                delay(5000L)
                Intent(this@MainActivity2,MainActivity3::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }

        GlobalScope.launch(Dispatchers.IO) {

            val time = measureTimeMillis {
               val answer1 = async { networkCall1() }
               val answer2 = async { networkCall1() }

                Log.d(TAG, "Answer 1 is ${answer1.await()}")
                Log.d(TAG, "Answer 2 is ${answer2.await()}")
            }
            Log.d(TAG, "Request took $time ms")
        }
    }

    suspend fun networkCall1(): String {
        delay(3000L)
        return "Answer 1"
    }

    suspend fun networkCall2(): String {
        delay(3000L)
        return "Answer 2"
    }
}