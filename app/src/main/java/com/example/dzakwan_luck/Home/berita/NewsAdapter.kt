package com.example.dzakwan_luck.Home.berita

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dzakwan_luck.Api.NewsModel
import com.example.dzakwan_luck.databinding.ItemNewsBannerBinding

class NewsAdapter(
    private val listNews: List<NewsModel>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: ItemNewsBannerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = listNews[position]
        holder.binding.tvNewsTitle.text = news.title

        Glide.with(holder.itemView.context)
            .load(news.image_url)
            .centerCrop()
            .into(holder.binding.ivNewsImage)

        // Fitur Clickable menggunakan Toast sesuai request
        holder.binding.root.setOnClickListener {
            onItemClick(news.title)
        }
    }

    override fun getItemCount(): Int = listNews.size
}