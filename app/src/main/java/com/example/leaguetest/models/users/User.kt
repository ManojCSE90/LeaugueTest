package com.example.leaguetest.models.users

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("name")
                val name: String,
                @SerializedName("id")
                val id: Int = 0,
                @SerializedName("avatar")
                val avatar: String? = null,
                @SerializedName("email")
                val email: String? = null,
                @SerializedName("username")
                val username: String? = null)