package com.codemobiles.android.cmroutineexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runDemo1()
    }

    private fun runDemo1() {
        GlobalScope.launch {

            while(true){
                delay(1000L)
                Log.d(TAG, "Inside of coroutine : ${Thread.currentThread().name}")
            }

        }

        Log.d(TAG, "Outside of coroutine : ${Thread.currentThread().name}")
    }
}