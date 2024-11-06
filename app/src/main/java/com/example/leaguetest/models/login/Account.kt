package com.example.leaguetest.models.login

import com.google.gson.annotations.SerializedName

data class Account(@SerializedName("api_key") val apiKey: String? = null)
