package com.example.leaguetest.models.post

import com.google.gson.annotations.SerializedName

data class Post(@SerializedName("id")
                val id: Int = 0,
                @SerializedName("title")
                val title: String? = null,
                @SerializedName("body")
                val body: String? = null,
                @SerializedName("userId")
                val userId: Int = 0)