package com.example.dzakwan_luck.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dzakwan_luck.data.dao.NoteDao
import com.example.dzakwan_luck.data.dao.PemeliharaanDao
import com.example.dzakwan_luck.data.entity.NoteEntity
import com.example.dzakwan_luck.data.entity.PemeliharaanEntity

@Database(
    entities = [NoteEntity::class, PemeliharaanEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun pemeliharaanDao(): PemeliharaanDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bina_desa_database"
                )
                    .fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
        }
    }
}

