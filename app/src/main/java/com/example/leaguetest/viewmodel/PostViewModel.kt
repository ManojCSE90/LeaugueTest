package com.example.leaguetest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leaguetest.models.NetworkResult
import com.example.leaguetest.models.post.UserPost
import com.example.leaguetest.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {

    val postOfUsers: LiveData<NetworkResult<List<UserPost>>> = repository.posts

    fun getPostOfUsers() {

        viewModelScope.launch(Dispatchers.IO) {
            repository.getPostOfUsers()
        }
    }
}