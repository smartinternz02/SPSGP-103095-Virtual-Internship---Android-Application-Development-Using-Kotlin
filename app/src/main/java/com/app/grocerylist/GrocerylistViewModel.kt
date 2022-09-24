package com.app.grocerylist

import androidx.lifecycle.ViewModel
import com.app.grocerylist.GrocerylistItems
import com.app.grocerylist.GrocerylistRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GrocerylistViewModel(private val repository: GrocerylistRepository) : ViewModel() {
    fun insert(items: GrocerylistItems) = GlobalScope.launch {
        repository.insert(items)
    }
    fun delete(items: GrocerylistItems) = GlobalScope.launch {
        repository.delete(items)
    }
    fun getAllGroceryItems() = repository.getAllItems()

}
