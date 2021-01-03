package com.gilboot.agriculturemarket.models

import com.gilboot.agriculturemarket.database.ProduceTable


enum class ProduceUnit { Kg, Pieces }


data class Produce(
    val id: String,
    val name: String,
    val price: Int,
    val unit: String,
    val date: String,
    val picture: String
)

fun Produce.asTable(): ProduceTable {
    return ProduceTable(
        id, name, price, unit, date, picture
    )
}

fun List<Produce>.asTable(): List<ProduceTable> = map { it.asTable() }