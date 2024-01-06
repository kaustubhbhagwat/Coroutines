package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        GlobalScope.launch(Dispatchers.IO) {
//            Log.d(TAG,"Starting Coroutine in thread ${Thread.currentThread().name}")
//            val ans = doNetworkCall()
//            withContext(Dispatchers.Main) {
//                Log.d(TAG,"Starting Coroutine in thread ${Thread.currentThread().name}")
//                binding.tvDummy.text = "Hello Kaustubh"
//            }
//            Log.d(TAG, ans)
//        }
//
//        Log.d(TAG,"Before runblocking")
//        runBlocking {
//            launch(Dispatchers.IO) {
//                delay(3000L)
//                Log.d(TAG,"Finished IO coroutine 1")
//            }
//            launch(Dispatchers.IO) {
//                delay(3000L)
//                Log.d(TAG,"Finished IO coroutine 2")
//            }
//            Log.d(TAG,"Start of runblocking")
//            delay(5000L)
//            Log.d(TAG,"End of runblocking")
//        }
//
//        Log.d(TAG,"After runblocking")

        val job = GlobalScope.launch(Dispatchers.Default) {
//            repeat(5){
//                Log.d(TAG,"Coroutine is still working")
//                delay(1000L)
//            }
            Log.d(TAG,"Starting long running calculation")
            withTimeout(3000L){
                for(i in 30..40){
                    if(isActive){
                        Log.d(TAG,"Result for i =$i: ${fib(i)}")
                    }
                }
            }
            Log.d(TAG,"Ending long running calculation")
        }
        runBlocking {
            delay(2000L)
            job.cancel()
            Log.d(TAG, "Cancelled Job")
        }
    }

    fun fib(n:Int): Long{
        return if(n==0) 0
        else if (n==1) 1
        else fib(n-1) + fib(n-2)

    }

    suspend fun doNetworkCall(): String {
        delay(3000L)
        return "This is the answer"
    }
}