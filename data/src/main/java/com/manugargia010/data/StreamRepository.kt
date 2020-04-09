package com.manugargia010.data

import com.manugargia010.domain.model.stream.Stream
import com.manugargia010.domain.model.Response
import io.reactivex.Single

class StreamRepository(private val streamRemoteSource: StreamRemoteSource) {
    fun getStreams() = streamRemoteSource.getRemoteStreams()
}

interface StreamRemoteSource {
    fun getRemoteStreams(): Single<Response<List<Stream>>>
}