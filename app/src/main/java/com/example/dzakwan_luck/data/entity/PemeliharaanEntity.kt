package com.example.dzakwan_luck.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pemeliharaan")
data class PemeliharaanEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tanggal: String,
    val namaAset: String,
    val kodeAset: String,
    val tindakan: String,
    val biaya: String,
    val adaBukti: Boolean,
    val imageUrl: String
)
