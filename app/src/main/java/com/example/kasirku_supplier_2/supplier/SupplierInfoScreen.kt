package com.example.kasirku_supplier_2.supplier

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.kasirku_supplier_2.entities.Supplier
import com.example.kasirku_supplier_2.ui.theme.AccentText
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import com.example.kasirku_supplier_2.R
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.example.kasirku_supplier_2.ui.theme.LightBackground
import com.example.kasirku_supplier_2.ui.theme.PrimaryBlue
import androidx.compose.ui.graphics.Color
import com.example.kasirku_supplier_2.ui.theme.DarkText
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun SupplierInfoScreen(supplier: Supplier, onClose: () -> Unit) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    BackHandler {
        onClose()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Informasi Supplier", color = AccentText) },
                backgroundColor = PrimaryBlue,
                actions = {
                    TextButton(onClick = onClose) {
                        Text("Tutup", color = AccentText)
                    }
                }
            )
        },
        backgroundColor = LightBackground
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                elevation = 8.dp,
                color = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.supplier_ic),
                        contentDescription = "Supplier Icon",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(bottom = 16.dp),
                    )

                    Text(
                        supplier.name,
                        color = DarkText,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "${supplier.address}, ${supplier.city}, ${supplier.province}, ${supplier.postalCode}",
                        color = DarkText,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        "Telp: ${supplier.phone}",
                        color = DarkText,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        "Email: ${supplier.email}",
                        color = DarkText,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

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
                            colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryBlue),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            Text("PANGGIL", color = Color.White)
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
                            colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryBlue),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            Text("EMAIL", color = Color.White)
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
                            colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryBlue),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            Text("WHATSAPP", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
