package com.manugargia010.domain.model.buff

import com.squareup.moshi.Json

data class Buff(

    @field:Json(name ="id") val id : Int,
    @field:Json(name ="client_id") val client_id : Int,
    @field:Json(name ="stream_id") val stream_id : Int,
    @field:Json(name ="time_to_show") val time_to_show : Int,
    @field:Json(name ="priority") val priority : Int,
    @field:Json(name ="created_at") val created_at : String,
    @field:Json(name ="author") val author : Author,
    @field:Json(name ="question") val question : Question,
    @field:Json(name ="answers") val answers : List<Answer>,
    @field:Json(name ="language") val language : String
)
