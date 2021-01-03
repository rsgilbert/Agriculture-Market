package com.gilboot.agriculturemarket.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MarketDao {
    @Query("SELECT * FROM ProduceTable ORDER BY id DESC")
    fun getAllProduce(): LiveData<List<ProduceTable>>

    @Query("SELECT * FROM ProduceTable WHERE id = :produceId LIMIT 1")
    suspend fun getOneProduceSusp(produceId: String): ProduceTable

    @Insert
    suspend fun insertOneProduce(produceTable: ProduceTable)
}