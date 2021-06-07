package com.wpjm.escapeeatingalone.Activity

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.wpjm.escapeeatingalone.Adapter.ListAdapter
import com.wpjm.escapeeatingalone.Model.Menu
import com.wpjm.escapeeatingalone.R


class MenuAdapter(val menuList:ArrayList<Menu>) :RecyclerView.Adapter<MenuAdapter.CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.CustomViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.menu_rv_item,parent,false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    private lateinit var itemClickListener : MenuAdapter.OnItemClickListener

    override fun onBindViewHolder(holder: MenuAdapter.CustomViewHolder, position: Int) {
        holder.image.setImageResource(menuList.get(position).image)
        holder.name.text=menuList.get(position).name
        /*
        // 리스트 눌렀을 때
        holder.itemView.setOnClickListener {
            var intent = Intent(holder.itemView?.context, MenuDetailActivity::class.java)
            intent.putExtra("MenuType", "${holder.name.text}")
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
         */


        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image=itemView.findViewById<ImageView>(R.id.iv_menuImage)
        val name=itemView.findViewById<TextView>(R.id.tv_menuName)
    }

}