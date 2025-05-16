package com.example.kasirku_supplier_2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kasirku_supplier_2.models.Supplier

@Database(entities = [Supplier::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun supplierDao(): SupplierDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "kasirku_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
