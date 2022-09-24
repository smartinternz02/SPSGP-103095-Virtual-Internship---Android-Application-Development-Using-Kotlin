package com.app.grocerylist

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), GrocerylistAdapter.GroceryItemClickInterface {
    lateinit var itemsRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    lateinit var list: List<GrocerylistItems>
    lateinit var grocerylistAdapter: GrocerylistAdapter
    lateinit var grocerylistViewModel: GrocerylistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemsRV = findViewById(R.id.rvItems)
        addFAB = findViewById(R.id.addFabbtn)
        list = ArrayList<GrocerylistItems>()
        grocerylistAdapter = GrocerylistAdapter(list, this)
        itemsRV.layoutManager = LinearLayoutManager(this)
        itemsRV.adapter = grocerylistAdapter
        val grocerylistRepository = GrocerylistRepository(GrocerylistDatabase(this))
        val factory = GrocerylistViewModelFactory(grocerylistRepository)
        grocerylistViewModel = ViewModelProvider(this, factory).get(GrocerylistViewModel::class.java)
        grocerylistViewModel.getAllGroceryItems().observe(this, Observer {
            grocerylistAdapter.list = it
            grocerylistAdapter.notifyDataSetChanged()
        })

        addFAB.setOnClickListener {
            openDialog()
        }
    }

    fun openDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.grocery_additem_dialogbox)
        val cancelBtn = dialog.findViewById<Button>(R.id.cancelBtn)
        val addBtn = dialog.findViewById<Button>(R.id.addBtn)
        val itemEdit = dialog.findViewById<EditText>(R.id.editItemName)
        val itemPriceEdit = dialog.findViewById<EditText>(R.id.editItemPrice)
        val itemQuantityEdit = dialog.findViewById<EditText>(R.id.editItemQuantity)
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        addBtn.setOnClickListener {
            val itemName: String = itemEdit.text.toString()
            val itemPrice: String = itemPriceEdit.text.toString()
            val itemQuantity: String = itemQuantityEdit.text.toString()
            if (itemName.isBlank() && itemPrice.isBlank() && itemQuantity.isBlank()) {
                Toast.makeText(applicationContext, "Please Enter all the data..", Toast.LENGTH_SHORT).show()

            } else {
                val pr = itemPrice.toInt()
                val qty= itemQuantity.toInt()
                val items = GrocerylistItems(itemName, qty, pr)
                grocerylistViewModel.insert(items)
                Toast.makeText(applicationContext, "Item Inserted..", Toast.LENGTH_SHORT).show()
                grocerylistAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }

        }
        dialog.show()
    }

    override fun onItemClick(grocerylistItems: GrocerylistItems) {
        grocerylistViewModel.delete(grocerylistItems)
        grocerylistAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext, "Item Deleted..", Toast.LENGTH_SHORT).show()
    }
}
