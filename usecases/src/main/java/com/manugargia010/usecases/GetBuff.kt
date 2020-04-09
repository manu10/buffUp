package com.manugargia010.usecases

import com.manugargia010.data.BuffRepository

class GetBuff (private val buffRepository: BuffRepository) {
    operator fun invoke(buffId: Int) = buffRepository.getBuff(buffId)
}