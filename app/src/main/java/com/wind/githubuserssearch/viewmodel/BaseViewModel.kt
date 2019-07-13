package com.wind.githubuserssearch.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val subscriptions = CompositeDisposable()

    protected fun addSubscriptions(vararg subscriptions: Disposable) = this.subscriptions.addAll(*subscriptions)

    override fun onCleared() {
        super.onCleared()

        subscriptions.dispose()
    }
}