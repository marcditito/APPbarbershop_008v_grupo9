package com.example.barbershopapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * Data access object used by Room to perform CRUD operations on
 * [Product] entities. Exposing LiveData allows the UI to observe
 * changes and update automatically when the database contents change.
 */
@Dao
interface ProductDao {
    /** Returns a list of all products as observable LiveData. */
    @Query("SELECT * FROM Product")
    fun getAll(): LiveData<List<Product>>

    /** Inserts a new product into the database. */
    @Insert
    suspend fun insert(product: Product)

    /** Deletes an existing product from the database. */
    @Delete
    suspend fun delete(product: Product)
}