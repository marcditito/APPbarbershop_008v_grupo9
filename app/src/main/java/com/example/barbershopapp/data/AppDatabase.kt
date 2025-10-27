package com.example.barbershopapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * The main database of the app. It uses the Room persistence library to
 * provide an abstraction layer over SQLite. The database exposes a
 * singleton instance via [getDatabase] so that only one database is
 * created during the lifetime of the application.
 */
@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        // The volatile annotation ensures that writes to this field are
        // immediately made visible to other threads.
        @Volatile
        private var instance: AppDatabase? = null

        /**
         * Returns the singleton database instance, creating it if necessary.
         */
        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "barbershop_db"
                ).build().also { instance = it }
            }
    }
}