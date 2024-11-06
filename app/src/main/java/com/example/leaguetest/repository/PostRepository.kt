package com.example.leaguetest.repository

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.leaguetest.R
import com.example.leaguetest.apiservice.ApiService
import com.example.leaguetest.models.NetworkResult
import com.example.leaguetest.models.post.UserPost
import com.example.leaguetest.models.users.User
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val resources: Resources,
    private val apiService: ApiService
) {

    private val _posts = MutableLiveData<NetworkResult<List<UserPost>>>()
    val posts: LiveData<NetworkResult<List<UserPost>>> = _posts

    suspend fun getPostOfUsers() {

        try {

            //show loader while getting response from server
            _posts.postValue(NetworkResult.Loading())

            //get api key from server
            val apiKey = getApiKey()

            if (apiKey == null) {
                postError()
                return
            }

            //get users list from server
            val users = getUsers(apiKey)

            if (users.isEmpty()) {
                postError()
                return
            }

            val userPosts = mutableListOf<UserPost>()

            //get posts of each user
            users.forEach { user ->
                val response = apiService.getPost(accessToken = apiKey, userId = user.id)

                if (response.isSuccessful && response.body() != null) {
                    response.body()?.forEach { post ->
                        userPosts.add(UserPost(user, post))
                    }
                }
            }

            //check if user posts list is empty or not
            if (userPosts.isEmpty()) {
                postError()
                return
            }
            //show user posts list
            _posts.postValue(
                NetworkResult.Success(userPosts)
            )
        } catch (e: Exception) {
            _posts.postValue(
                NetworkResult.Failure(
                    e.message ?: resources.getString(R.string.something_went_wrong)
                )
            )

        }
    }

    private fun postError() {
        _posts.postValue(
            NetworkResult.Failure(
                resources.getString(R.string.something_went_wrong)
            )
        )
    }

    private suspend fun getUsers(apiKey: String): List<User> {
        val response = apiService.getUsers(apiKey)
        return if (response.isSuccessful && response.body() != null) {
            response.body() ?: emptyList()
        } else {
            emptyList()
        }
    }

    private suspend fun getApiKey(): String? {

        val username = "hello"
        val password = "world"

        val credential = "Basic " + android.util.Base64.encodeToString(
            "$username:$password".toByteArray(),
            android.util.Base64.NO_WRAP
        )

        val response = apiService.login(credential)

        return if (response.isSuccessful && response.body() != null) {
            response.body()?.apiKey
        } else {
            null
        }
    }
}