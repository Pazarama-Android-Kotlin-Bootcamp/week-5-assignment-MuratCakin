package com.muratcakin.userposts.data.remote.api

import com.muratcakin.userposts.data.model.Users
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("users")
    fun getUsers(): Call<List<Users>>

    @DELETE("users/{id}")
    fun deleteUsers(@Path("{id}") id: String): Call<Users>
}