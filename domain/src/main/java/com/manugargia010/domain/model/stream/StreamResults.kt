package com.manugargia010.domain.model.stream

import com.squareup.moshi.Json

data class StreamResults (
	@field:Json(name = "result") val stream : List<Stream>
)