package com.example.dzakwan_luck.data.Api
import retrofit2.http.GET

interface NewsApiService {
    // Mengambil 5 berita terbaru
    @GET("articles/?limit=5")
    suspend fun getLatestNews(): NewsResponse
}

