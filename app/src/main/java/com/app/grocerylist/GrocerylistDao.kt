package com.app.grocerylist

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GrocerylistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: GrocerylistItems)

    @Delete
    suspend fun delete(item:GrocerylistItems)

    @Query("SELECT * FROM grocerry_items")
    fun getAllGroceryItems() : LiveData<List<GrocerylistItems>>
}