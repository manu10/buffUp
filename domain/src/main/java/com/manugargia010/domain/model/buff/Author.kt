package com.manugargia010.domain.model.buff

import com.squareup.moshi.Json

data class Author (

	@field:Json(name ="first_name") val first_name : String,
	@field:Json(name ="last_name") val last_name : String,
	@field:Json(name ="image") val image : String
)