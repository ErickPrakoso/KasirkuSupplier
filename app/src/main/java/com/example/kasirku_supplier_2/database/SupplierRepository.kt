package com.example.kasirku_supplier_2.database

import com.example.kasirku_supplier_2.dao.SupplierDao
import com.example.kasirku_supplier_2.entities.Supplier
import kotlinx.coroutines.flow.Flow

class SupplierRepository(private val dao: SupplierDao) {
    val allSuppliers: Flow<List<Supplier>> = dao.getAll()

    suspend fun insert(supplier: Supplier) = dao.insert(supplier)
    suspend fun update(supplier: Supplier) = dao.update(supplier)
    suspend fun delete(supplier: Supplier) = dao.delete(supplier)
}