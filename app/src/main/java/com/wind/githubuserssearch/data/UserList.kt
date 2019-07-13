package com.wind.githubuserssearch.data

import com.google.gson.annotations.SerializedName

data class UserList (
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("items") val usersInfo: List<UserInfo>
)