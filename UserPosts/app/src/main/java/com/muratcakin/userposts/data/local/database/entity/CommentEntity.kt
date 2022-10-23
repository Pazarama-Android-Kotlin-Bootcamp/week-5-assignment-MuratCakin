package com.muratcakin.userposts.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.muratcakin.userposts.utils.Constants

@Entity(tableName = Constants.TABLE_COMMENT_NAME)
data class CommentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long? = null,
    @ColumnInfo(name = "commentId") val commentId: String?,
    @ColumnInfo(name = "commentEmail") val commentEmail: String?,
    @ColumnInfo(name = "commentBody") val commentBody: String?,
)