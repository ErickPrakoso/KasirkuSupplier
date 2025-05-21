package com.example.kasirku_supplier_2.ui.viewmodel

import android.content.Context
import com.example.kasirku_supplier_2.utils.CsvExporter
import java.io.File
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kasirku_supplier_2.database.SupplierRepository
import com.example.kasirku_supplier_2.entities.Supplier
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first

class SupplierViewModel(private val repository: SupplierRepository) : ViewModel() {
    val suppliers = repository.allSuppliers
        .map { it.sortedBy { s -> s.id } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addSupplier(supplier: Supplier) {
        viewModelScope.launch { repository.insert(supplier) }
    }

    fun updateSupplier(supplier: Supplier) {
        viewModelScope.launch { repository.update(supplier) }
    }

    fun deleteSupplier(supplier: Supplier) {
        viewModelScope.launch { repository.delete(supplier) }
    }

    fun exportToCsv(context: Context, onComplete: (File) -> Unit) {
        viewModelScope.launch {
            val list = repository.allSuppliers.first()
            val csv = CsvExporter.exportToCsv(list)
            val file = CsvExporter.saveCsvFile(context, csv)
            onComplete(file)
        }
    }
}

class SupplierViewModelFactory(private val repository: SupplierRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SupplierViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SupplierViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
