package com.muratcakin.userposts.data.di

import com.muratcakin.userposts.data.local.database.UsersDatabase
import com.muratcakin.userposts.data.remote.api.UserService
import com.muratcakin.userposts.data.repository.UsersRepository
import com.muratcakin.userposts.data.repository.UsersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class UserModule {

    @Provides
    fun provideUserService(retrofit: Retrofit) : UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    fun provideUserRepository(userService: UserService, usersDatabase: UsersDatabase) : UsersRepository {
        return UsersRepositoryImpl(userService, usersDatabase)
    }
}