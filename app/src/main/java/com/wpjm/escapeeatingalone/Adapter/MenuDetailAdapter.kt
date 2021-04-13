package com.wpjm.escapeeatingalone.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wpjm.escapeeatingalone.Model.MenuDetailModel
import com.wpjm.escapeeatingalone.R

class MenuDetailAdapter (val menuList: ArrayList<MenuDetailModel>) : RecyclerView.Adapter<MenuDetailAdapter.CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuDetailAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu_detail, parent, false)
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener {
                val currentPosison : Int = adapterPosition
                val currentMenu : MenuDetailModel = menuList.get(currentPosison)
            }
        }

    }

    override fun onBindViewHolder(holder: MenuDetailAdapter.CustomViewHolder, position: Int) {
        holder.menuImage.setImageResource(menuList.get(position).menuImage)
        holder.name.text = menuList.get(position).name
        holder.explanation.text = menuList.get(position).explanation
    }

    override fun getItemCount(): Int {
        return menuList.size

    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val menuImage = itemView.findViewById<ImageView>(R.id.menuDetail_imageView)
        val name = itemView.findViewById<TextView>(R.id.menuDetail_textView_name)
        val explanation = itemView.findViewById<TextView>(R.id.menuDetail_textView_explanation)

    }
}