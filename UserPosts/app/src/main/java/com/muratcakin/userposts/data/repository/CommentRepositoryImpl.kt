package com.muratcakin.userposts.data.repository

import com.muratcakin.userposts.data.local.database.CommentsDatabase
import com.muratcakin.userposts.data.local.database.entity.CommentEntity
import com.muratcakin.userposts.data.model.Comment
import com.muratcakin.userposts.data.remote.api.CommentService
import retrofit2.Call

class CommentRepositoryImpl constructor(
    private val commentService: CommentService,
    private val commentsDatabase: CommentsDatabase
) : CommentRepository {
    override fun getComments(): Call<List<Comment>> {
        return commentService.getComments()
    }

    override fun getCommentById(id: Int): Call<Comment> {
        return commentService.getCommentById(id)
    }

    override fun getCommentByIdForFavorite(id: Int): CommentEntity? {
        return commentsDatabase.commentDao().getCommentById(id.toString())
    }

    override fun insertFavoriteComment(comment: CommentEntity) {
        return commentsDatabase.commentDao().insert(comment)
    }

    override fun deleteFavoriteComment(id: String) {
        return commentsDatabase.commentDao().deleteFavoriteById(id)
    }

    override fun getFavorites(): List<CommentEntity> {
        return commentsDatabase.commentDao().getAllComments()
    }
}