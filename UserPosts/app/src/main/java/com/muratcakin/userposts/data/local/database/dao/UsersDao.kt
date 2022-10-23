package com.muratcakin.userposts.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.muratcakin.userposts.data.local.database.base.BaseDao
import com.muratcakin.userposts.data.local.database.entity.PostEntity
import com.muratcakin.userposts.data.local.database.entity.UsersEntity
import com.muratcakin.userposts.utils.Constants

@Dao
interface UsersDao : BaseDao<UsersEntity> {
    @Query("SELECT * FROM ${Constants.TABLE_USERS_NAME}")
    fun getAllUsers(): List<UsersEntity>

    @Query("DELETE FROM ${Constants.TABLE_USERS_NAME}")
    fun deleteAll()

    @Query("SELECT * FROM ${Constants.TABLE_USERS_NAME} WHERE usersId = :usersId")
    fun getUsersById(usersId: String): UsersEntity?

}