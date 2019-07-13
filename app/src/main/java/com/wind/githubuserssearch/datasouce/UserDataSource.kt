package com.wind.githubuserssearch.datasouce

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.wind.githubuserssearch.data.UserInfo
import com.wind.githubuserssearch.services.GithubService
import org.koin.core.KoinComponent
import org.koin.core.inject

class UserDataSource(val query: String) : PageKeyedDataSource<Int, UserInfo>(), KoinComponent {
    private val service: GithubService by inject()
    private val INITIAL_PAGE = 1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, UserInfo>
    ) {
        val response = service.getUserList(query, INITIAL_PAGE).execute()
        if (response.isSuccessful) {
            response.body()?.run {
                callback.onResult(response.body()!!.usersInfo, null, 2)
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, UserInfo>
    ) {
        val response = service.getUserList(query, params.key).execute()
        if (response.isSuccessful) {
            response.body()?.run {
                callback.onResult(response.body()!!.usersInfo, params.key + 1)
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, UserInfo>
    ) {
        // we don't need it now, leave it empty
    }
}

class UserDataFactory(private val search: String) : DataSource.Factory<Int, UserInfo>() {
    override fun create(): DataSource<Int, UserInfo> {
        return UserDataSource(search)
    }
}