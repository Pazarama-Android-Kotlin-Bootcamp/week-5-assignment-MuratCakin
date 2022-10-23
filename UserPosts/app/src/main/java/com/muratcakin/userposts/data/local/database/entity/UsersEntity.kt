package com.muratcakin.userposts.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.muratcakin.userposts.utils.Constants

@Entity(tableName = Constants.TABLE_USERS_NAME)
data class UsersEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long? = null,
    @ColumnInfo(name = "usersId") val usersId: String?,
    @ColumnInfo(name = "usersUsername") val usersUsername: String?,
    @ColumnInfo(name = "usersEmail") val usersEmail: String?,
)