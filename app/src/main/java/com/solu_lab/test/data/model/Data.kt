package com.solu_lab.test.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "totalItems") val totalItems: Int,
    @Json(name = "list") val list: List<CoinItem>
)
