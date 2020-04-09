package com.manugargia010.buffup.framework.di

import com.manugargia010.buffup.framework.service.BuffDataAccessService
import com.manugargia010.buffup.framework.service.BuffRemoteSourceImpl
import com.manugargia010.buffup.framework.service.StreamRemoteSourceImpl
import com.manugargia010.buffup.ui.streamlist.StreamListPresenter
import com.manugargia010.buffup.ui.videoplayer.VideoPlayerPresenter
import com.manugargia010.data.BuffRemoteSource
import com.manugargia010.data.BuffRepository
import com.manugargia010.data.StreamRemoteSource
import com.manugargia010.data.StreamRepository
import com.manugargia010.usecases.GetBuff
import com.manugargia010.usecases.GetStreams
import dagger.Module
import dagger.Provides


@Module
class StreamModule {

    @Provides
    fun provideStreamRemoteSource(buffDataAccessService: BuffDataAccessService): StreamRemoteSource
            = StreamRemoteSourceImpl(buffDataAccessService)

    @Provides
    fun provideStreamRepository(streamRemoteSource: StreamRemoteSource): StreamRepository
            = StreamRepository(streamRemoteSource)

    @Provides
    fun provideGetStreams(streamRepository: StreamRepository): GetStreams
            = GetStreams(streamRepository)

    @Provides
    fun provideStreamListPresenter(
        view: StreamListPresenter.View,
        getStreams: GetStreams
    ): StreamListPresenter =
        StreamListPresenter(view, getStreams)

    @Provides
    fun provideBuffRemoteSource(buffDataAccessService: BuffDataAccessService): BuffRemoteSource
            = BuffRemoteSourceImpl(buffDataAccessService)

    @Provides
    fun provideBuffRepository(buffRemoteSource: BuffRemoteSource): BuffRepository
            = BuffRepository(buffRemoteSource)

    @Provides
    fun provideGetBuff(buffRepository: BuffRepository): GetBuff
            = GetBuff(buffRepository)

    @Provides
    fun provideVideoPlayerPresenter(
        view: VideoPlayerPresenter.View,
        getBuff: GetBuff
    ): VideoPlayerPresenter =
        VideoPlayerPresenter(view, getBuff)

}