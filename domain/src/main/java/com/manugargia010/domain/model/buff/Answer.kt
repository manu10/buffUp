package com.manugargia010.domain.model.buff

import com.manugargia010.domain.model.stream.Image
import com.squareup.moshi.Json

data class Answer (

	@field:Json(name ="id") val id : Int,
	@field:Json(name ="buff_id") val buff_id : Int,
	@field:Json(name ="title") val title : String,
	@field:Json(name ="image") val image : Image
)