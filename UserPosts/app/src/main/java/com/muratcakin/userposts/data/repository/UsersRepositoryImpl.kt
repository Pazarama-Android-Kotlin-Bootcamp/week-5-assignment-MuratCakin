package com.muratcakin.userposts.data.repository

import com.muratcakin.userposts.data.local.database.UsersDatabase
import com.muratcakin.userposts.data.local.database.entity.UsersEntity
import com.muratcakin.userposts.data.model.Users
import com.muratcakin.userposts.data.remote.api.ApiService
import com.muratcakin.userposts.data.remote.api.UserService
import retrofit2.Call

class UsersRepositoryImpl  constructor(
    private val userService: UserService,
    private val usersDatabase: UsersDatabase
    ) : UsersRepository {
    override fun getUsers(): Call<List<Users>> {
        return userService.getUsers()
    }

    override fun getUsersById(id: Int): UsersEntity? {
        return  usersDatabase.usersDao().getUsersById(id.toString())
    }

}