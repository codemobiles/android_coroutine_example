package com.codemobiles.android.cmroutineexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.codemobiles.android.cmroutineexample.model.Comment
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        runDemo1()
//        runDemo2()
//        runDemo3()
//        runDemo4()
//        runDemo5()
//        runDemo6()
//        runDemo7()
//        runDemo8()
//        runDemo9()
//        runDemo10()
//        runDemo11()
//        runDemo12()
        runDemo13()
    }

    private fun runDemo13() {
        val handler = CoroutineExceptionHandler{_, throwable->
            Log.e(TAG, "Caught exceptiion: $throwable")
        }

        GlobalScope.launch(Dispatchers.IO + handler) {
            delay(3000L)
            launch{
                throw Exception("Hey Error")
            }
        }
    }

    suspend fun feedComments1(): List<Comment> {
        val api = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIInterface::class.java)

        val response = api.getComments().execute()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            return arrayListOf()
        }
    }

    private fun runDemo12() {

        GlobalScope.launch {
            val commentsList1 = async { feedComments1() }
            val commentsList2 = async { feedComments1() }
            Log.d(TAG, "Result1: ${commentsList1.await()[0].name}, Result2: ${commentsList2.await()[1].name}")

            withContext(Dispatchers.Main) {
                title = title.toString() + ": ${commentsList1.await().count()}"
            }
        }

//        api.getComments().enqueue(object : Callback<List<Comment>> {
//            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
//                for (comment in response.body()!!) {
//                    Log.d(TAG, comment.name)
//                }
//            }
//
//            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
//                Log.d(TAG, "Error")
//            }
//        })
    }

    private fun runDemo11() {
//        GlobalScope.launch {
//            repeat(100){
//                delay(1000)
//                Log.d(TAG, "Running...")
//            }
//        }

        lifecycleScope.launch {
            repeat(100) {
                delay(1000)
                Log.d(TAG, "Running...")
            }
        }


    }

    fun handleClickBtn(view: View) {
        Intent(applicationContext, SecondActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }

    private fun runDemo10() {
        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                val answer1 = async { feedDataFromNetwork1() } // 3 secs
                val answer2 = async { feedDataFromNetwork2() } // 2 secs
                Log.d(TAG, "Answer1 : ${answer1.await()}")
                Log.d(TAG, "Answer2 : ${answer2.await()}")
            }
            Log.d(TAG, "Took : $time")
        }
    }

    private fun runDemo9() {
        val job = GlobalScope.async {
            Log.d(TAG, "Start:")
            delay(3000)
            "Result Ok"
        }

        GlobalScope.launch {
            Log.d(TAG, "Begin:")
            Log.d(TAG, "${job.await()}")
        }
    }

    private fun runDemo8() {
        val job = GlobalScope.launch {
            var count = 0
            repeat(1000) {
                if (isActive) {
                    count += 1
                    Log.d(TAG, "Count is $count")
                    delay(10)
                }
            }
            if (isActive) {
                ///...
            }

        }

        GlobalScope.launch {
            delay(5000)
            job.cancel()
        }
    }

    private fun runDemo7() {
        GlobalScope.launch {
            withTimeout(3000) {
                var count = 0
                repeat(1000) {
                    count += 1
                    Log.d(TAG, "Count is $count")
                    delay(1000)
                }
            }
        }
    }


    private fun runDemo6() {

        val job = GlobalScope.launch {
            var count = 0
            repeat(1000) {
                count += 1
                Log.d(TAG, "Count is $count")
                delay(1000)
            }
        }

        GlobalScope.launch {
            delay(5000)
            job.cancel()
        }

    }

    private fun runDemo5() {
        runBlocking {
            Log.d(TAG, "Inside of coroutine runBlocking : ${Thread.currentThread().name}")
            delay(10000L)
        }

        Log.d(TAG, "Outside of coroutine runBlocking : ${Thread.currentThread().name}")
    }

    private fun runDemo4() {
        // Coroutine Context (main and thread pool (default and io))
        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Inside of coroutine : ${Thread.currentThread().name}")
            val answer = feedDataFromNetwork1()

            withContext(Dispatchers.Main) {
                Log.d(TAG, "The answer is $answer")
                title = answer
            }
        }
    }


    private fun runDemo3() {
        GlobalScope.launch {

            val time = measureTimeMillis {
                doSomethingLog()
            }
            Log.d(TAG, "It took $time sec.")
        }
        Log.d(TAG, "Exit from GlobalScope")
    }

    private suspend fun doSomethingLog() {
        var i = 0
        for (e in 0..1000000000) {
            i++
        }
    }

    private fun runDemo2() {
        // suspend function
        GlobalScope.launch {
            val answer1 = feedDataFromNetwork1()
            val answer2 = feedDataFromNetwork2()
            Log.d(TAG, "Answer1 is $answer1")
            Log.d(TAG, "Answer2 is $answer2")
        }
    }

    suspend fun feedDataFromNetwork1(): String {
        delay(3000L)
        return "Dog"
    }

    suspend fun feedDataFromNetwork2(): String {
        delay(2000L)
        return "Cat"
    }

    private fun runDemo1() {
        GlobalScope.launch {

            while (true) {
                delay(1000L)
                Log.d(TAG, "Inside of coroutine : ${Thread.currentThread().name}")
            }

        }

        Log.d(TAG, "Outside of coroutine : ${Thread.currentThread().name}")
    }


}