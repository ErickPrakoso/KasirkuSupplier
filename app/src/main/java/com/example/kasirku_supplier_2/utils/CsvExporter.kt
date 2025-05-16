package com.example.kasirku_supplier_2.utils

import android.content.Context
import android.os.Environment
import com.example.kasirku_supplier_2.models.Supplier
import java.io.File
import java.io.FileOutputStream

object CsvExporter {

    fun exportToCsv(suppliers: List<Supplier>): String {
        return buildString {
            append("ID,Nama,Email,Telepon,Alamat,Kota,Provinsi,Kode Pos\n")
            suppliers.forEach {
                append("${it.id},${it.name},${it.email},${it.phone},${it.address},${it.city},${it.province},${it.postalCode}\n")
            }
        }
    }

    fun saveCsvFile(context: Context, csv: String): File {
        val downloadsDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            ?: context.filesDir // fallback untuk device lama

        val file = File(downloadsDir, "suppliers_${System.currentTimeMillis()}.csv")
        FileOutputStream(file).use { it.write(csv.toByteArray()) }
        return file
    }
}
