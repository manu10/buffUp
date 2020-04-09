package com.manugargia010.domain.model.stream

import com.squareup.moshi.Json

data class Image (

	@field:Json(name = "id") val id : String,
	@field:Json(name = "key") val key : String,
	@field:Json(name = "url") val url : String
)