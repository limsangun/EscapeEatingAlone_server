package com.wpjm.escapeeatingalone.Activity

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.wpjm.escapeeatingalone.Model.MenuDetailModel
import com.wpjm.escapeeatingalone.Model.PartyModel
import com.wpjm.escapeeatingalone.R


class PartyAdapter(val partyList:ArrayList<PartyModel>) :RecyclerView.Adapter<PartyAdapter.CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyAdapter.CustomViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_party,parent,false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return partyList.size
    }

    override fun onBindViewHolder(holder: PartyAdapter.CustomViewHolder, position: Int) {
        holder.date.text = partyList.get(position).date
        holder.title.text = partyList.get(position).title
        holder.count.text = partyList.get(position).count
        holder.storeName.text = partyList.get(position).storeName
    }
    
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date = itemView.findViewById<TextView>(R.id.partyActivity_textview_date)
        val title = itemView.findViewById<TextView>(R.id.partyActivity_textview_title)
        val count = itemView.findViewById<TextView>(R.id.partyActivity_textview_count)
        val storeName = itemView.findViewById<TextView>(R.id.partyActivity_textview_storeName)
    }
}
