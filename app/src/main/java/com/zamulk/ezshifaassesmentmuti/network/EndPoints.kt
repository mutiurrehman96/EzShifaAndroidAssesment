package com.zamulk.ezshifaassesmentmuti.network

import com.zamulk.ezshifaassesmentmuti.models.PostsModel
import retrofit2.Call
import retrofit2.http.*


interface EndPoints {

    @GET("/photos")
    fun getAllPosts(): Call<List<PostsModel>>

}