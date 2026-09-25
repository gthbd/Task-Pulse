package com.example.taskpulse.data

import com.example.taskpulse.model.Quote
import retrofit2.http.GET

interface QuoteApiService {
    @GET("quotes/random")
    suspend fun getRandomQuote(): Quote
}