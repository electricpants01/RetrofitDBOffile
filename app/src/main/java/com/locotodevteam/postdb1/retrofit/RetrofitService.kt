package com.locotodevteam.postdb1.retrofit

import com.locotodevteam.postdb1.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("{id}")
    suspend fun getPostById(@Path("id") id: Int): Response<Post>
}