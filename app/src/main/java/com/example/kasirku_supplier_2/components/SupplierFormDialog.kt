package com.example.kasirku_supplier_2.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import com.example.kasirku_supplier_2.ui.theme.AccentGreen
import com.example.kasirku_supplier_2.ui.theme.AccentText
import com.example.kasirku_supplier_2.ui.theme.BorderGray
import com.example.kasirku_supplier_2.ui.theme.DarkCard

@Composable
fun SupplierFormDialog(
    isEditing: Boolean,
    name: String,
    onNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    phone: String,
    onPhoneChange: (String) -> Unit,
    address: String,
    onAddressChange: (String) -> Unit,
    city: String,
    onCityChange: (String) -> Unit,
    province: String,
    onProvinceChange: (String) -> Unit,
    postalCode: String,
    onPostalCodeChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (isEditing) "Edit Supplier" else "Tambah Supplier",
                    fontWeight = FontWeight.Bold,
                    color = AccentText,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), // Padding around the form fields
                verticalArrangement = Arrangement.spacedBy(8.dp) // Space between fields
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = onNameChange,
                    label = { Text("Nama Supplier", color = AccentText) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(textColor = AccentText),
                    modifier = Modifier.fillMaxWidth() // Ensure it takes full width
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = onEmailChange,
                    label = { Text("Email", color = AccentText) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(textColor = AccentText),
                    modifier = Modifier.fillMaxWidth() // Ensure it takes full width
                )
                OutlinedTextField(
                    value = phone,
                    onValueChange = onPhoneChange,
                    label = { Text("Telepon", color = AccentText) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(textColor = AccentText),
                    modifier = Modifier.fillMaxWidth() // Ensure it takes full width
                )
                OutlinedTextField(
                    value = address,
                    onValueChange = onAddressChange,
                    label = { Text("Alamat", color = AccentText) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(textColor = AccentText),
                    modifier = Modifier.fillMaxWidth() // Ensure it takes full width
                )
                OutlinedTextField(
                    value = city,
                    onValueChange = onCityChange,
                    label = { Text("Kota", color = AccentText) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(textColor = AccentText),
                    modifier = Modifier.fillMaxWidth() // Ensure it takes full width
                )
                OutlinedTextField(
                    value = province,
                    onValueChange = onProvinceChange,
                    label = { Text("Provinsi", color = AccentText) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(textColor = AccentText),
                    modifier = Modifier.fillMaxWidth() // Ensure it takes full width
                )
                OutlinedTextField(
                    value = postalCode,
                    onValueChange = onPostalCodeChange,
                    label = { Text("Kode Pos", color = AccentText) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(textColor = AccentText),
                    modifier = Modifier.fillMaxWidth() // Ensure it takes full width
                )
            }
        },
        backgroundColor = DarkCard,
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(backgroundColor = AccentGreen),
                modifier = Modifier.fillMaxWidth() // Button takes full width
            ) {
                Text("Simpan", color = AccentText)
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(backgroundColor = BorderGray),
                modifier = Modifier.fillMaxWidth() // Button takes full width
            ) {
                Text("Batal", color = AccentText)
            }
        }
    )
}