package com.muratcakin.userposts.data.repository

import com.muratcakin.userposts.data.local.database.entity.PostEntity
import com.muratcakin.userposts.data.model.Post
import retrofit2.Call

interface PostRepository {
    fun getPosts(): Call<List<Post>>
    fun getPostById(id: Int): PostEntity?
    fun getPostByIdForFavorite(id: Int): PostEntity?
    fun insertFavoritePost(post: PostEntity)
    fun deleteFavoritePost(id: String)
    fun getFavorites(): List<PostEntity>
}