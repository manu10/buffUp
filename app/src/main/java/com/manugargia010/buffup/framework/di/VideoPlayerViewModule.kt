package com.manugargia010.buffup.framework.di

import com.manugargia010.buffup.ui.videoplayer.VideoPlayerActivity
import com.manugargia010.buffup.ui.videoplayer.VideoPlayerPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class VideoPlayerViewModule {
    @Binds
    abstract fun bindVideoPlayerView(streamListActivity: VideoPlayerActivity): VideoPlayerPresenter.View
}
