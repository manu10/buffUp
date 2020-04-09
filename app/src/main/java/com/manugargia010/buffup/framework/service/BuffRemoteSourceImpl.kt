package com.manugargia010.buffup.framework.service

import com.manugargia010.data.BuffRemoteSource
import com.manugargia010.domain.model.Response
import com.manugargia010.domain.model.buff.Buff
import io.reactivex.Single

class BuffRemoteSourceImpl(private val buffDataAccessService: BuffDataAccessService) : BuffRemoteSource {
    override fun getRemoteBuff(buffId: Int): Single<Response<Buff>> {
        return buffDataAccessService.getStreamBuffs(buffId)
            .map { Response.Success(it.buff) as Response<Buff> }
            .onErrorReturn { Response.Error(it) }
    }

}