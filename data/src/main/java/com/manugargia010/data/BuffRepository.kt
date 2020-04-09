package com.manugargia010.data

import com.manugargia010.domain.model.Response
import com.manugargia010.domain.model.buff.Buff
import io.reactivex.Single

class BuffRepository(private val buffRemoteSource: BuffRemoteSource) {
    fun getBuff(buffId: Int) = buffRemoteSource.getRemoteBuff(buffId)
}

interface BuffRemoteSource {
    fun getRemoteBuff(buffId: Int): Single<Response<Buff>>
}