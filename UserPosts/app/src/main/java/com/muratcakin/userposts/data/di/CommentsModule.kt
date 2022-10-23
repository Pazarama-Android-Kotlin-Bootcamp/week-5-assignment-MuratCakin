package com.muratcakin.userposts.data.di

import com.muratcakin.userposts.data.local.database.CommentsDatabase
import com.muratcakin.userposts.data.remote.api.CommentService
import com.muratcakin.userposts.data.repository.CommentRepository
import com.muratcakin.userposts.data.repository.CommentRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class CommentsModule {

    @Provides
    fun provideCommentService(retrofit: Retrofit) : CommentService {
        return retrofit.create(CommentService::class.java)
    }

    @Provides
    fun provideCommentRepository(commentService: CommentService, commentsDatabase: CommentsDatabase) : CommentRepository {
        return CommentRepositoryImpl(commentService, commentsDatabase)
    }
}