package com.locotodevteam.postdb1.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.locotodevteam.postdb1.model.Post

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getPostDao(): PostDao

    companion object{
        fun getRoomInstance(context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "firstPostDB").build()
    }
}