package com.muratcakin.userposts.data.remote.api

import com.muratcakin.userposts.data.model.Comment
import com.muratcakin.userposts.data.model.Post
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentService {
    @GET("comments")
    fun getComments(): Call<List<Comment>>

    @GET("comments/{id}")
    fun getCommentById(@Path("id") id: Int): Call<Comment>

    @DELETE("comments/{id}")
    fun deleteComment(@Path("{id}") id: String): Call<Comment>
}