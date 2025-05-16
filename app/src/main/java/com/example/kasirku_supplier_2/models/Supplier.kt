package com.example.kasirku_supplier_2.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suppliers")
data class Supplier(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val city: String,
    val province: String,
    val postalCode: String
)
