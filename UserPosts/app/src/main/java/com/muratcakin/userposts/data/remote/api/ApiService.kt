package com.muratcakin.userposts.data.remote.api

import com.muratcakin.userposts.data.model.Post
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("posts/{id}")
    fun getPostById(@Path("id") id: Int): Call<Post>

    @DELETE("posts/{id}")
    fun deletePost(@Path("{id}") id: String): Call<Post>
}