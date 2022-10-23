package com.muratcakin.userposts.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.muratcakin.userposts.data.local.database.base.BaseDao
import com.muratcakin.userposts.data.local.database.entity.CommentEntity
import com.muratcakin.userposts.utils.Constants

@Dao
interface CommentDao : BaseDao<CommentEntity> {
    @Query("SELECT * FROM ${Constants.TABLE_COMMENT_NAME}")
    fun getAllComments(): List<CommentEntity>

    @Query("DELETE FROM ${Constants.TABLE_COMMENT_NAME}")
    fun deleteAll()

    @Query("SELECT * FROM ${Constants.TABLE_COMMENT_NAME} WHERE commentId = :commentId")
    fun getCommentById(commentId: String): CommentEntity?

    @Query("DELETE FROM ${Constants.TABLE_COMMENT_NAME} WHERE commentId = :commentId")
    fun deleteFavoriteById(commentId: String)

}