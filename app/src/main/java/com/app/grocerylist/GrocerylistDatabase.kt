package com.app.grocerylist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GrocerylistItems::class], version = 1)
abstract class GrocerylistDatabase : RoomDatabase() {
    abstract fun getGroceryDao(): GrocerylistDao

    companion object {
        @Volatile
        private var instance: GrocerylistDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }


        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                GrocerylistDatabase::class.java,
                "Grocery.db"
            ).build()
    }
}