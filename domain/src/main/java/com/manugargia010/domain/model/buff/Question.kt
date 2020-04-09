package com.manugargia010.domain.model.buff

import com.squareup.moshi.Json

data class Question (

	@field:Json(name ="id") val id : Int,
	@field:Json(name ="title") val title : String,
	@field:Json(name ="category") val category : Int
)