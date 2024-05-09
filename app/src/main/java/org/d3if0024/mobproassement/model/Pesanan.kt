package org.d3if0024.mobproassement.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pesanan_1")
data class Pesanan(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nama: String,
    val size: String,
    val topping: String,
    val drink: String
)
