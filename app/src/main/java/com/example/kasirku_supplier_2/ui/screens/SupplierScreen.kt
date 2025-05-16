package com.example.kasirku_supplier_2.ui.screens

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import com.example.kasirku_supplier_2.components.SupplierItem
import com.example.kasirku_supplier_2.models.Supplier
import com.example.kasirku_supplier_2.ui.theme.*
import com.example.kasirku_supplier_2.ui.viewmodel.SupplierViewModel

@Composable
fun SupplierScreen(viewModel: SupplierViewModel, onShowInfo: (Supplier) -> Unit) {
    val context = LocalContext.current
    val supplierList by viewModel.suppliers.collectAsState(initial = emptyList())

    var showForm by remember { mutableStateOf(false) }
    var editingSupplier by remember { mutableStateOf<Supplier?>(null) }
    var dropdownExpandedId by remember { mutableStateOf<Int?>(null) }
    var searchText by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var province by remember { mutableStateOf("") }
    var postalCode by remember { mutableStateOf("") }

    var exportCsvTrigger by remember { mutableStateOf(false) }

    val onEdit: (Supplier) -> Unit = { supplier ->
        editingSupplier = supplier
        name = supplier.name
        email = supplier.email
        phone = supplier.phone
        address = supplier.address
        city = supplier.city
        province = supplier.province
        postalCode = supplier.postalCode
        showForm = true
    }

    val onDelete: (Supplier) -> Unit = { supplier ->
        viewModel.deleteSupplier(supplier)
        Toast.makeText(context, "Supplier deleted", Toast.LENGTH_SHORT).show()
    }

    val filteredSuppliers = supplierList.filter {
        it.name.contains(searchText, ignoreCase = true) || it.address.contains(searchText, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (isSearchActive) {
                        OutlinedTextField(
                            value = searchText,
                            onValueChange = { searchText = it },
                            placeholder = { Text("Cari supplier...", color = AccentText) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                textColor = AccentText,
                                cursorColor = AccentGreen
                            )
                        )
                    } else {
                        Text("Supplier", color = AccentText)
                    }
                },
                backgroundColor = DarkCard,
                actions = {
                    if (!isSearchActive) {
                        IconButton(onClick = { isSearchActive = true }) {
                            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search", tint = AccentText)
                        }
                        IconButton(onClick = {
                            exportCsvTrigger = true
                        }) {
                            Text("CSV", color = AccentText)
                        }
                    } else {
                        IconButton(onClick = {
                            isSearchActive = false
                            searchText = ""
                        }) {
                            Icon(imageVector = Icons.Filled.Close, contentDescription = "Close", tint = AccentText)
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    editingSupplier = null
                    name = ""
                    email = ""
                    phone = ""
                    address = ""
                    city = ""
                    province = ""
                    postalCode = ""
                    showForm = true
                },
                backgroundColor = AccentGreen
            ) {
                Text("+", color = Color.White, fontSize = 24.sp)
            }
        },
        backgroundColor = DarkBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(filteredSuppliers) { supplier ->
                    SupplierItem(
                        supplier = supplier,
                        onClick = { onShowInfo(supplier) },
                        onEdit = onEdit,
                        onDelete = onDelete,
                        expandedId = dropdownExpandedId,
                        onExpandChange = { dropdownExpandedId = it }
                    )
                }
            }
        }
    }

    LaunchedEffect(exportCsvTrigger) {
        if (exportCsvTrigger) {
            viewModel.exportToCsv(context) { file ->
                Toast.makeText(
                    context,
                    "CSV disimpan di:\n${file.name}",
                    Toast.LENGTH_SHORT
                ).show()

                val uri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.provider",
                    file
                )

                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/csv"
                    putExtra(Intent.EXTRA_STREAM, uri)
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }

                context.startActivity(
                    Intent.createChooser(
                        intent,
                        "Bagikan file CSV ke:"
                    )
                )
            }
            exportCsvTrigger = false
        }
    }

    if (showForm) {
        AlertDialog(
            onDismissRequest = { showForm = false },
            title = {
                Text(
                    text = if (editingSupplier != null) "Edit Supplier" else "Tambah Supplier",
                    fontWeight = FontWeight.Bold,
                    color = AccentText
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nama Supplier", color = AccentText) }, colors = TextFieldDefaults.outlinedTextFieldColors(textColor = AccentText))
                    OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email", color = AccentText) }, colors = TextFieldDefaults.outlinedTextFieldColors(textColor = AccentText))
                    OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Telepon", color = AccentText) }, colors = TextFieldDefaults.outlinedTextFieldColors(textColor = AccentText))
                    OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("Alamat", color = AccentText) }, colors = TextFieldDefaults.outlinedTextFieldColors(textColor = AccentText))
                    OutlinedTextField(value = city, onValueChange = { city = it }, label = { Text("Kota", color = AccentText) }, colors = TextFieldDefaults.outlinedTextFieldColors(textColor = AccentText))
                    OutlinedTextField(value = province, onValueChange = { province = it }, label = { Text("Provinsi", color = AccentText) }, colors = TextFieldDefaults.outlinedTextFieldColors(textColor = AccentText))
                    OutlinedTextField(value = postalCode, onValueChange = { postalCode = it }, label = { Text("Kode Pos", color = AccentText) }, colors = TextFieldDefaults.outlinedTextFieldColors(textColor = AccentText))
                }
            },
            backgroundColor = DarkCard,
            confirmButton = {
                Button(onClick = {
                    val supplier = Supplier(
                        id = editingSupplier?.id ?: 0,
                        name = name,
                        email = email,
                        phone = phone,
                        address = address,
                        city = city,
                        province = province,
                        postalCode = postalCode
                    )

                    if (editingSupplier != null) {
                        viewModel.updateSupplier(supplier)
                    } else {
                        viewModel.addSupplier(supplier)
                    }
                    showForm = false
                }, colors = ButtonDefaults.buttonColors(backgroundColor = AccentGreen)) {
                    Text("Simpan", color = AccentText)
                }
            },
            dismissButton = {
                Button(onClick = { showForm = false }, colors = ButtonDefaults.buttonColors(backgroundColor = BorderGray)) {
                    Text("Batal", color = AccentText)
                }
            }
        )
    }
}
