package com.manugargia010.buffup.framework.service

import com.manugargia010.domain.model.buff.BuffResult
import com.manugargia010.domain.model.stream.StreamResults
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface BuffDataAccessService {

    @GET("streams")
    fun fetchStreams(): Single<StreamResults>

    @GET("buffs/{buff_id}")
    fun getStreamBuffs(@Path("buff_id") id: Int): Single<BuffResult>

}
