package com.gilboot.agriculturemarket

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gilboot.agriculturemarket.database.MarketDao
import com.gilboot.agriculturemarket.models.Produce
import java.util.*

class Repository(marketDao: MarketDao) {

    fun getAllProduces(): LiveData<List<Produce>> {

        return liveData {
            emit(
                listOf(
                    Produce("1", "Tomato", 2000, "kg", Date()),
                    Produce("2", "Banana", 5000, "pieces", Date()),
                    Produce("3", "Maize", 4000, "20000", Date())
                )
            )
        }
    }

}