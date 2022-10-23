package com.muratcakin.userposts.data.di

import android.content.Context
import com.muratcakin.userposts.data.local.database.CommentsDatabase
import com.muratcakin.userposts.data.local.database.PostsDatabase
import com.muratcakin.userposts.data.local.database.UsersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    @Singleton
    fun providePostsDatabase(@ApplicationContext appContext: Context): PostsDatabase {
        return PostsDatabase.getDatabase(appContext)
    }

    @Singleton
    @Provides
    fun providePostDao(db: PostsDatabase) = db.postDao()

    @Provides
    @Singleton
    fun provideCommentsDatabase(@ApplicationContext appContext: Context): CommentsDatabase {
        return CommentsDatabase.getDatabase(appContext)
    }

    @Singleton
    @Provides
    fun provideCommentDao(db: CommentsDatabase) = db.commentDao()

    @Provides
    @Singleton
    fun provideUsersDatabase(@ApplicationContext appContext: Context): UsersDatabase {
        return UsersDatabase.getDatabase(appContext)
    }

    @Singleton
    @Provides
    fun provideUserDao(db: UsersDatabase) = db.usersDao()
}