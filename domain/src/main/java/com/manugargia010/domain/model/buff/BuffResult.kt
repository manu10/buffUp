package com.manugargia010.domain.model.buff

import com.squareup.moshi.Json

data class BuffResult (
	@field:Json(name = "result") val buff : Buff
)