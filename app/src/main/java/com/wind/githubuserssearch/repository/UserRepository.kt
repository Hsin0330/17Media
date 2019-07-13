package com.wind.githubuserssearch.repository

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.wind.githubuserssearch.Constant
import com.wind.githubuserssearch.data.UserInfo
import com.wind.githubuserssearch.datasouce.UserDataFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent

class UserRepository : KoinComponent {
    fun getUsers(query: String): Observable<PagedList<UserInfo>> {
        val dataSource = UserDataFactory(query)
        val config = PagedList.Config.Builder()
            .setPageSize(Constant.PAGE_SIZE)
            .setPrefetchDistance(Constant.THRESHOLD)
            .build()

        return RxPagedListBuilder(dataSource, config)
            .setFetchScheduler(Schedulers.io())
            .setNotifyScheduler(AndroidSchedulers.mainThread())
            .buildObservable()
    }
}