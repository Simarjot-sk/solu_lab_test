package com.solu_lab.test.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinItem(
    @Json(name = "_id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "pictures") val pictures: Picture?
)

@JsonClass(generateAdapter = true)
data class Picture(
    @Json(name = "front") val frontImage: PictureItem
)

@JsonClass(generateAdapter = true)
data class PictureItem(
    @Json(name = "url") val url: String
)