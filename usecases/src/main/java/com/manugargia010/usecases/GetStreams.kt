package com.manugargia010.usecases
import com.manugargia010.data.StreamRepository

class GetStreams (private val streamsRepository: StreamRepository) {
    operator fun invoke() = streamsRepository.getStreams()
}