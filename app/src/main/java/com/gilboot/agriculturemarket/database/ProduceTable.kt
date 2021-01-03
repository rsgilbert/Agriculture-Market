package com.gilboot.agriculturemarket.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gilboot.agriculturemarket.models.Produce

@Entity
data class ProduceTable(
    @PrimaryKey
    val id: String,
    val name: String,
    val price: Int,
    val unit: String,
    val date: String,
    val picture: String
)

fun ProduceTable.asModel(): Produce {
    return Produce(
        id, name, price, unit, date, picture
    )
}

fun List<ProduceTable>.asModel(): List<Produce> = map { it.asModel() }