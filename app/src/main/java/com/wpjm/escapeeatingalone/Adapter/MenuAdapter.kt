package com.wpjm.escapeeatingalone.Activity

import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.wpjm.escapeeatingalone.Model.Menu
import com.wpjm.escapeeatingalone.R

class MenuAdapter(val menuList:ArrayList<Menu>) :RecyclerView.Adapter<MenuAdapter.CustomViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.CustomViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.menu_rv_item,parent,false)
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener {
                val curPos:Int=adapterPosition
                val menu:Menu=menuList.get(curPos)
                Toast.makeText(parent.context,"이름:${menu.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBindViewHolder(holder: MenuAdapter.CustomViewHolder, position: Int) {
        holder.image.setImageResource(menuList.get(position).image)
        holder.name.text=menuList.get(position).name

    }

    override fun getItemCount(): Int {
        return menuList.size
    }


    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image=itemView.findViewById<ImageView>(R.id.iv_menuImage)
        val name=itemView.findViewById<TextView>(R.id.tv_menuName)
    }

}