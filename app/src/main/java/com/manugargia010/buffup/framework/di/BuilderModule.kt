package com.manugargia010.buffup.framework.di

import com.manugargia010.buffup.ui.streamlist.StreamListActivity
import com.manugargia010.buffup.ui.videoplayer.VideoPlayerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [StreamModule::class, StreamListViewModule::class])
    abstract fun bindStreamListActivity(): StreamListActivity  // Add bindings for other sub-components here
    @ContributesAndroidInjector(modules = [StreamModule::class, VideoPlayerViewModule::class])
    abstract fun bindVideoPlayerActivity(): VideoPlayerActivity  // Add bindings for other sub-components here
}