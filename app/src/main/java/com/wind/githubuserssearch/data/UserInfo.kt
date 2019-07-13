package com.wind.githubuserssearch.data

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("id")val id: Long,
    @SerializedName("login")val name: String,
    @SerializedName("avatar_url") val avatarUrl: String
)