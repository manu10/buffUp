package com.manugargia010.buffup.ui.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BasePresenter<V> (val view: V) {
    val disposables = CompositeDisposable()

    open fun onCreate() { }

    open fun onDestroy() {
        disposables.dispose()
    }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

}