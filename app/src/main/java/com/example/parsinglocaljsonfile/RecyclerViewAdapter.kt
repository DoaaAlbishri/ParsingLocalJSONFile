package com.example.parsinglocaljsonfile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerViewAdapter(private val mainActivity: MainActivity , private val images: ArrayList<String>) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>(){
    class ItemViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_row
                        ,parent
                        ,false
                )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val image =images[position]

        holder.itemView.apply{
            Glide.with(this)
                .load(image)
                .into(imageView)
            //open Image in full screen
            llItem.setOnClickListener {
                mainActivity.openImage(image)
            }
        }

    }

    override fun getItemCount(): Int =images.size

}