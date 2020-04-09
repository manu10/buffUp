package com.manugargia010.domain.model.stream

import com.squareup.moshi.Json

data class Images (
    //todo: change name and implement a custom serializer
    @field:Json(name = "0") val imageLowResolution : Image,
    @field:Json(name = "1") val imageMediumResolution : Image,
    @field:Json(name = "2") val imageHighResolution : Image
)