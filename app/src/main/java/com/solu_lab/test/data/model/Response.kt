package com.solu_lab.test.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Response(
    @Json(name = "result") val result: Int,
    @Json(name = "msg") val message:String,
    @Json(name = "data") val data: Data
)