package com.wind.githubuserssearch.services

import com.wind.githubuserssearch.data.UserList
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("/search/users")
    fun getUserList(@Query("q") query: String,@Query("page") page: Int) : Call<UserList>
}