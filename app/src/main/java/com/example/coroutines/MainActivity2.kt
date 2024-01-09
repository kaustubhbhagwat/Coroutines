package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class MainActivity2 : AppCompatActivity() {

    val TAG = "MainActivity2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

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