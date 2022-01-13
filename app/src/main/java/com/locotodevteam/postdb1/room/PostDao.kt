package com.locotodevteam.postdb1.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.locotodevteam.postdb1.model.Post

@Dao
interface PostDao {

    @Query("SELECT * FROM Post")
    fun getAll(): LiveData<List<Post>>

    @Insert(onConflict = REPLACE)
    suspend fun insert(post: Post)

    @Insert
    fun insertAll(mnyPosts: List<Post>)

    @Delete
    fun delete(post: Post)
}