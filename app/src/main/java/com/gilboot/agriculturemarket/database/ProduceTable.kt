package com.gilboot.agriculturemarket.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProduceTable(
    @PrimaryKey
    val id: String,
    val name: String,
    val price: Int,
    val unit: String,
    val dateTimestamp: Long
)