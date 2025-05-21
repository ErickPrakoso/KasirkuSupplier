package com.example.kasirku_supplier_2.supplier

import androidx.compose.runtime.*
import com.example.kasirku_supplier_2.entities.Supplier
import com.example.kasirku_supplier_2.ui.viewmodel.SupplierViewModel

@Composable
fun SupplierApp(viewModel: SupplierViewModel) {
    var selectedSupplier by remember { mutableStateOf<Supplier?>(null) }

    if (selectedSupplier != null) {
        SupplierInfoScreen(supplier = selectedSupplier!!) {
            selectedSupplier = null
        }
    } else {
        SupplierScreen(
            viewModel = viewModel,
            onShowInfo = { selectedSupplier = it }
        )
    }
}
