package com.codemobiles.android.cmroutineexample

import com.codemobiles.android.cmroutineexample.model.Comment
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET("/comments")
    fun getComments(): Call<List<Comment>>

}