package com.muratcakin.userposts.data.repository

import com.muratcakin.userposts.data.local.database.entity.CommentEntity
import com.muratcakin.userposts.data.model.Comment
import retrofit2.Call

interface CommentRepository {
    fun getComments(): Call<List<Comment>>
    fun getCommentById(id: Int): Call<Comment>
    fun getCommentByIdForFavorite(id: Int): CommentEntity?
    fun insertFavoriteComment(comment: CommentEntity)
    fun deleteFavoriteComment(id: String)
    fun getFavorites(): List<CommentEntity>
}