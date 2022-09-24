package com.app.grocerylist

class GrocerylistRepository(private val db: GrocerylistDatabase) {
    suspend fun insert(items: GrocerylistItems) = db.getGroceryDao().insert(items)
    suspend fun delete(items: GrocerylistItems) = db.getGroceryDao().delete(items)

    fun getAllItems() = db.getGroceryDao().getAllGroceryItems()
}