package com.muratcakin.userposts.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.muratcakin.userposts.data.local.database.converter.DaoConverter
import com.muratcakin.userposts.data.local.database.dao.UsersDao
import com.muratcakin.userposts.data.local.database.entity.UsersEntity
import com.muratcakin.userposts.utils.Constants

@Database(entities = [UsersEntity::class], version = 1, exportSchema = false)
@TypeConverters(DaoConverter::class)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao

    companion object {
        private var instance: UsersDatabase? = null

        fun getDatabase(context: Context): UsersDatabase = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, UsersDatabase::class.java, Constants.TABLE_NAME_USERS_DATABASE)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }
}