package com.app.grocerylist
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GrocerylistViewModelFactory (private val repository: GrocerylistRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T: ViewModel> create(modelClass: Class<T>):T{
        return GrocerylistViewModel(repository) as T
    }
}