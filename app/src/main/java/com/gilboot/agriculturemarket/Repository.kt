package com.gilboot.agriculturemarket

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.gilboot.agriculturemarket.database.MarketDao
import com.gilboot.agriculturemarket.database.asModel
import com.gilboot.agriculturemarket.models.Produce
import com.gilboot.agriculturemarket.models.asTable
import timber.log.Timber
import java.util.*

class Repository(private val marketDao: MarketDao) {
    suspend fun getOneProduce(produceId: String): Produce =
        marketDao.getOneProduceSusp(produceId).asModel()

    fun getAllProduces(): LiveData<List<Produce>> = marketDao.getAllProduce().map { it.asModel() }

    suspend fun addProduce(
        name: String,
        price: Int,
        unit: String,
        dateOfAvailability: String,
        picture: String
    ): Produce? {
        return try {
            val produce = Produce(
                id = Date().time.toString(),
                name = name,
                price = price,
                unit = unit,
                date = dateOfAvailability,
                picture = picture
            )
            marketDao.insertOneProduce(produce.asTable())
            produce
        } catch (e: Exception) {
            Timber.e("Failed to add produce: $e")
            e.printStackTrace()
            null
        }
    }
}
//    fun getAllProduces(): LiveData<List<Produce>> {
//        return liveData {
//            emit(
//                listOf(
//                    Produce("1", "Tomato", 2000, "kg", Date().toLocaleString()),
//                    Produce("2", "Banana", 5000, "pieces", Date().toLocaleString()),
//                    Produce("3", "Maize", 4000, "20000", Date().toLocaleString())
//                )
//            )
//        }
//    }