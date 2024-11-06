package com.example.leaguetest.apiservice

import com.example.leaguetest.models.login.Account
import com.example.leaguetest.models.post.Post
import com.example.leaguetest.models.users.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("login")
    suspend fun login(@Header("Authorization") credentials: String?): Response<Account>

    @GET("users")
    suspend fun getUsers(@Header("x-access-token") accessToken: String?): Response<List<User>>

    @GET("posts")
    suspend fun getPost(@Header("x-access-token") accessToken: String?, @Query("userId") userId: Int): Response<List<Post>>


}