package com.example.day24recyclerview

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(var newsArrayList: ArrayList<News>, var context : Activity) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private  lateinit var myListener : onItemClickListener
    interface onItemClickListener {
        fun onItemClicking(position: Int)
    }

    fun setItemClickListener(listener: onItemClickListener){
        myListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
         //to create new view instance
         //when layout manager fails to find a suitable view for each item

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return MyViewHolder(itemView, myListener)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        //populate items with data
        val currentItem = newsArrayList[position]
        holder.hTitle.text = currentItem.newsHeading
        holder.hImage.setImageResource(currentItem.newsImage)
    }

    override fun getItemCount(): Int {
                         //how many list item are present in your array
        return newsArrayList.size
    }
    class MyViewHolder(itemView : View,listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        //it holds the view so views are not created everytime, so memory can be saved
        val hTitle = itemView.findViewById<TextView>(R.id.tvHeading)
        val hImage = itemView.findViewById<ShapeableImageView>(R.id.headingImage)

        init{
            itemView.setOnClickListener {
                  listener.onItemClicking(adapterPosition)
            }
        }
    }

}