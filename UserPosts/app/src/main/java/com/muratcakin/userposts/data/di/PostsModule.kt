package com.muratcakin.userposts.data.di

import com.muratcakin.userposts.data.local.database.PostsDatabase
import com.muratcakin.userposts.data.remote.api.ApiService
import com.muratcakin.userposts.data.repository.PostRepository
import com.muratcakin.userposts.data.repository.PostRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class PostsModule {

    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun providePostRepository(apiService: ApiService, postsDatabase: PostsDatabase) : PostRepository {
        return PostRepositoryImpl(apiService, postsDatabase)
    }
}