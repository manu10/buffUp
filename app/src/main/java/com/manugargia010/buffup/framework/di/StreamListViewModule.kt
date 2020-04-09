package com.manugargia010.buffup.framework.di

import com.manugargia010.buffup.ui.streamlist.StreamListActivity
import com.manugargia010.buffup.ui.streamlist.StreamListPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class StreamListViewModule {

    @Binds
    abstract fun bindStreamListView (streamListActivity: StreamListActivity): StreamListPresenter.View
}