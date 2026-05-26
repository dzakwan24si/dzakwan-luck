package com.example.dzakwan_luck.Api

data class NewsResponse(
    val results: List<NewsModel>
)

data class NewsModel(
    val id: Int,
    val title: String,
    val image_url: String,
    val published_at: String
)
