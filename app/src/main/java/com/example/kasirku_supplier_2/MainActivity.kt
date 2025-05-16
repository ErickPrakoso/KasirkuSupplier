package com.example.kasirku_supplier_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kasirku_supplier_2.data.db.AppDatabase
import com.example.kasirku_supplier_2.data.repository.SupplierRepository
import com.example.kasirku_supplier_2.ui.screens.SupplierApp
import com.example.kasirku_supplier_2.ui.theme.KasirkuTheme
import com.example.kasirku_supplier_2.ui.viewmodel.SupplierViewModel
import com.example.kasirku_supplier_2.ui.viewmodel.SupplierViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val db = AppDatabase.getDatabase(this)
            val repository = SupplierRepository(db.supplierDao())
            val viewModel: SupplierViewModel = viewModel(factory = SupplierViewModelFactory(repository))

            KasirkuTheme {
                SupplierApp(viewModel)
            }
        }
    }
}
