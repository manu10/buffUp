package com.manugargia010.buffup.framework.service

import com.manugargia010.data.StreamRemoteSource
import com.manugargia010.domain.model.Response
import com.manugargia010.domain.model.stream.Stream
import io.reactivex.Single

class StreamRemoteSourceImpl(private val buffDataAccessService: BuffDataAccessService) : StreamRemoteSource {
    override fun getRemoteStreams(): Single<Response<List<Stream>>> {
        return buffDataAccessService.fetchStreams()
            .map { Response.Success(it.stream) as Response<List<Stream>> }
            .onErrorReturn { Response.Error(it) }
    }

}