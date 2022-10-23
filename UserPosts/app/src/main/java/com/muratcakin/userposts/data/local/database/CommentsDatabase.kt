package com.muratcakin.userposts.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.muratcakin.userposts.data.local.database.converter.DaoConverter
import com.muratcakin.userposts.data.local.database.dao.CommentDao
import com.muratcakin.userposts.data.local.database.entity.CommentEntity
import com.muratcakin.userposts.utils.Constants

@Database(entities = [CommentEntity::class], version = 1, exportSchema = false)
@TypeConverters(DaoConverter::class)
abstract class CommentsDatabase : RoomDatabase() {
    abstract fun commentDao(): CommentDao

    companion object {
        private var instance: CommentsDatabase? = null

        fun getDatabase(context: Context): CommentsDatabase = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, CommentsDatabase::class.java, Constants.TABLE_NAME_COMMENTS_DATABASE)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

    }
}