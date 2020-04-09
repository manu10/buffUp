package com.manugargia010.domain.model.stream

import com.squareup.moshi.Json

data class Stream (
    @field:Json(name = "id") val id : Int,
    @field:Json(name = "title") val title : String,
    @field:Json(name = "description") val description : String,
    @field:Json(name = "url") val url : String,
    @field:Json(name = "start_at") val start_at : String,
    @field:Json(name = "end_at") val end_at : String,
    @field:Json(name = "image") val image : Images,
    @field:Json(name = "logo") val logo : Logos
)