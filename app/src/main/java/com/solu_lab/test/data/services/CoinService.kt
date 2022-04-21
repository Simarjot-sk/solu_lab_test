package com.solu_lab.test.data.services

import com.solu_lab.test.data.model.Response
import retrofit2.http.GET

interface CoinService {
    @GET("coinlist")
    suspend fun getCoins(): Response
}