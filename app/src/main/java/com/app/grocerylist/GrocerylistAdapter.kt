package com.app.grocerylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.grocerylist.GrocerylistItems

class GrocerylistAdapter(var list: List<GrocerylistItems>, val groceryItemClickInterface: GroceryItemClickInterface): RecyclerView.Adapter<GrocerylistAdapter.GroceryViewHolder>() {

    inner class GroceryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameTV = itemView.findViewById<TextView>(R.id.ItemName)
        val quantityTV = itemView.findViewById<TextView>(R.id.Quantity)
        val rateTV = itemView.findViewById<TextView>(R.id.Rate)
        val amountTV = itemView.findViewById<TextView>(R.id.totalAmt)
        val deleteTV = itemView.findViewById<ImageView>(R.id.Delete)
    }

    interface GroceryItemClickInterface{
        fun onItemClick(grocerylistItems: GrocerylistItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_itemview, parent, false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        holder.nameTV.text = list.get(position).itemName
        holder.quantityTV.text = list.get(position).itemQuantity.toString()
        holder.rateTV.text = "Rs. "+ list.get(position).itemPrice.toString()
        val itemTotal: Int = list.get(position).itemPrice * list.get(position).itemQuantity
        holder.amountTV.text = "Rs. "+ itemTotal.toString()
        holder.deleteTV.setOnClickListener{
            groceryItemClickInterface.onItemClick(list.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}



























