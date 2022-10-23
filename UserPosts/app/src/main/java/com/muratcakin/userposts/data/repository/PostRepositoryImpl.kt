package com.muratcakin.userposts.data.repository

import com.muratcakin.userposts.data.local.database.PostsDatabase
import com.muratcakin.userposts.data.local.database.entity.PostEntity
import com.muratcakin.userposts.data.remote.api.ApiService
import com.muratcakin.userposts.data.model.Post
import retrofit2.Call

class PostRepositoryImpl constructor(
    private val apiService: ApiService,
    private val postsDatabase: PostsDatabase
    ) : PostRepository {
    override fun getPosts(): Call<List<Post>> {
        return apiService.getPosts()
    }

    override fun getPostById(id: Int): PostEntity? {
        return  postsDatabase.postDao().getPostById(id.toString())
    }

    override fun insertFavoritePost(post: PostEntity) {
        return postsDatabase.postDao().insert(post)
    }

    override fun deleteFavoritePost(id: String) {
        return postsDatabase.postDao().deleteFavoriteById(id)
    }

    override fun getFavorites(): List<PostEntity> {
        return postsDatabase.postDao().getAllPosts()
    }

    override fun getPostByIdForFavorite(id: Int): PostEntity? {
        return postsDatabase.postDao().getPostById(id.toString())
    }
}