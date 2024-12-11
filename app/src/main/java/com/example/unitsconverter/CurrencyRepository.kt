package com.example.unitsconverter

import android.accounts.NetworkErrorException

class CurrencyRepository(private val apiService: CurrencyApiService) {
    suspend fun getLatestRates(apiKey: String): ExchangeRatesResponse {
        return try {
            apiService.getExchangeRates(apiKey)
        } catch (e: Exception) {
            throw NetworkErrorException("Failed to fetch exchange rates", e)
        }
    }
}