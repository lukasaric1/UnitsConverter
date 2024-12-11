package com.example.unitsconverter

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class ExchangeRatesResponse(
    val success: Boolean,
    val rates: Map<String, Double>,
    val base: String
)

interface CurrencyApiService {
    @GET("latest")
    suspend fun getExchangeRates(
        @Query("access_key") apiKey: String
    ): ExchangeRatesResponse

    companion object {
        fun create(): CurrencyApiService {
            return Retrofit.Builder()
                .baseUrl("https://api.exchangerate-api.com/v4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CurrencyApiService::class.java)
        }
    }
}