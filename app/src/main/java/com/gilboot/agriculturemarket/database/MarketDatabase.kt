package com.gilboot.agriculturemarket.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 * class responsible for creating sqlite database
 */

@Database(
    entities = [ProduceTable::class],
    version = 1,
    exportSchema = false
)
abstract class MarketDatabase : RoomDatabase() {
    abstract val marketDao: MarketDao

    companion object {
        @Volatile
        private var INSTANCE: MarketDatabase? = null

        fun getInstance(context: Context): MarketDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MarketDatabase::class.java, "market_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }

}