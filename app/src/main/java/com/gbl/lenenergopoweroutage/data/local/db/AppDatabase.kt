package com.gbl.lenenergopoweroutage.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [OutageDBModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun outageDao(): OutageDao

    companion object {
        private const val DB_NAME = "outage_db"

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = instance ?: synchronized(this) {
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DB_NAME
            ).build().also { instance = it }
        }
    }
}