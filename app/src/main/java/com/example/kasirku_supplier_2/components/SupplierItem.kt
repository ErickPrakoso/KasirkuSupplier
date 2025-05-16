package com.example.kasirku_supplier_2.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kasirku_supplier_2.R
import com.example.kasirku_supplier_2.models.Supplier
import com.example.kasirku_supplier_2.ui.theme.AccentText
import com.example.kasirku_supplier_2.ui.theme.DarkCard

@Composable
fun SupplierItem(
    supplier: Supplier,
    onClick: () -> Unit,
    onEdit: (Supplier) -> Unit,
    onDelete: (Supplier) -> Unit,
    expandedId: Int?,
    onExpandChange: (Int?) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        backgroundColor = DarkCard,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Konten utama supplier
            Row(
                modifier = Modifier
                    .weight(1f), // Beri ruang penuh ke kiri
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.supplier_ic),
                    contentDescription = "Icon Supplier",
                    modifier = Modifier
                        .size(56.dp)
                        .padding(end = 8.dp),
                    contentScale = ContentScale.Fit
                )
                Column {
                    Text("ID : ${supplier.id}", color = AccentText, fontSize = 12.sp)
                    Text(supplier.name, fontWeight = FontWeight.Bold, color = AccentText, fontSize = 18.sp)
                    Text(
                        "${supplier.address}, ${supplier.city}, ${supplier.province}, ${supplier.postalCode}",
                        color = AccentText,
                        fontSize = 13.sp
                    )
                }
            }

            // Tombol overflow ⋮
            Box {
                IconButton(onClick = {
                    onExpandChange(if (expandedId == supplier.id) null else supplier.id)
                }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                DropdownMenu(
                    expanded = expandedId == supplier.id,
                    onDismissRequest = { onExpandChange(null) }
                ) {
                    DropdownMenuItem(onClick = {
                        onExpandChange(null)
                        onEdit(supplier)
                    }) {
                        Text("Edit")
                    }
                    DropdownMenuItem(onClick = {
                        onExpandChange(null)
                        onDelete(supplier)
                    }) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}
