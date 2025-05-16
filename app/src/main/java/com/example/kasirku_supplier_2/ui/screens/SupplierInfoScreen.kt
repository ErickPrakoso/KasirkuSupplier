package com.example.kasirku_supplier_2.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.kasirku_supplier_2.models.Supplier
import com.example.kasirku_supplier_2.ui.theme.AccentText
import com.example.kasirku_supplier_2.ui.theme.AccentGreen
import com.example.kasirku_supplier_2.ui.theme.DarkCard
import com.example.kasirku_supplier_2.ui.theme.DarkBackground
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.example.kasirku_supplier_2.R
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign

@Composable
fun SupplierInfoScreen(supplier: Supplier, onClose: () -> Unit) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    BackHandler {
        // Mencegah aplikasi keluar dan hanya menutup layar info
        onClose()  // Ini akan menjalankan fungsi untuk menutup SupplierInfoScreen dan kembali ke halaman sebelumnya
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Informasi Supplier", color = AccentText) },
                backgroundColor = DarkCard,
                actions = {
                    TextButton(onClick = onClose) {
                        Text("Tutup", color = AccentText)
                    }
                }
            )
        },
        backgroundColor = DarkBackground
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp)
                .background(DarkCard)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Supplier Icon at the top
            Image(
                painter = painterResource(id = R.drawable.supplier_ic),
                contentDescription = "Supplier Icon",
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 16.dp),
            )

            // Supplier Info
            Text(
                supplier.name,
                color = AccentText,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "${supplier.address}, ${supplier.city}, ${supplier.province}, ${supplier.postalCode}",
                color = AccentText,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )

            Text(
                "Telp: ${supplier.phone}",
                color = AccentText,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                "Email: ${supplier.email}",
                color = AccentText,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Action Buttons
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:${supplier.phone}")
                            }
                            context.startActivity(intent)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = AccentGreen),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text("PANGGIL", color = AccentText)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        coroutineScope.launch {
                            val intent = Intent(Intent.ACTION_SENDTO).apply {
                                data = Uri.parse("mailto:${supplier.email}")
                            }
                            context.startActivity(intent)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = AccentGreen),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text("EMAIL", color = AccentText)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        coroutineScope.launch {
                            val phone = supplier.phone.replace("+", "").replace(" ", "")
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse("https://wa.me/$phone")
                            }
                            context.startActivity(intent)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = AccentGreen),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text("WHATSAPP", color = AccentText)
                }
            }
        }
    }
}
