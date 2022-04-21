package com.solu_lab.test.data.repo

import com.solu_lab.test.data.model.CoinItem
import com.solu_lab.test.data.services.CoinService

class CoinRepository(
    private val coinService: CoinService
) {

    suspend fun getCoins(): List<CoinItem>{
        val response = coinService.getCoins()
        return response.data.list
    }
}