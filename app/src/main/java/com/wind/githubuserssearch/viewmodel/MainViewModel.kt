package com.wind.githubuserssearch.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.wind.githubuserssearch.data.UserInfo
import com.wind.githubuserssearch.repository.UserRepository

class MainViewModel(private val repository: UserRepository) : BaseViewModel() {

    val userInput = MutableLiveData<String>()
    val userList = MutableLiveData<PagedList<UserInfo>>()
    val waitingViewVisibility = MutableLiveData<Boolean>()

    fun onSearch() {
        userInput.value?.run {
            addSubscriptions(repository.getUsers(this)
                .doOnSubscribe { waitingViewVisibility.value = true }
                .doOnError { waitingViewVisibility.value = true }
                .doOnNext { waitingViewVisibility.value = true }
                .subscribe({ userList.value = it }, { Log.e(MainViewModel::class.java.simpleName, it.message) })
            )
        }
    }
}