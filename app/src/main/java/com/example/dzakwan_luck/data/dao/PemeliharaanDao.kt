package com.example.dzakwan_luck.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.dzakwan_luck.data.entity.PemeliharaanEntity

@Dao
interface PemeliharaanDao {
    @Query("SELECT * FROM pemeliharaan ORDER BY id DESC")
    suspend fun getAllPemeliharaan(): List<PemeliharaanEntity>

    @Insert
    suspend fun insertPemeliharaan(pemeliharaan: PemeliharaanEntity)

    @Delete
    suspend fun deletePemeliharaan(pemeliharaan: PemeliharaanEntity)
}
