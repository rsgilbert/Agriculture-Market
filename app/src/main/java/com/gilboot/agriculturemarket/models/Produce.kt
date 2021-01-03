package com.gilboot.agriculturemarket.models

import java.util.*


enum class ProduceUnit { Kg, Pieces }


data class Produce(
    val id: String,
    val name: String,
    val price: Int,
    val unit: String,
    val date: Date
)