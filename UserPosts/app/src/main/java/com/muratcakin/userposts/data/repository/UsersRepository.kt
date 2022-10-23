package com.muratcakin.userposts.data.repository

import com.muratcakin.userposts.data.local.database.entity.UsersEntity
import com.muratcakin.userposts.data.model.Users
import retrofit2.Call

interface UsersRepository {
    fun getUsers(): Call<List<Users>>
    fun getUsersById(id: Int): UsersEntity?
}