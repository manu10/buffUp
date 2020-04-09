package com.manugargia010.domain.model.stream

import com.squareup.moshi.Json

data class Logos (  //todo: change name and implement a custom serializer
    @field:Json(name = "0") val logoLowResolution : Image,
    @field:Json(name = "1") val logoMediumResolution : Image,
    @field:Json(name = "2") val logoHighResolution : Image
)