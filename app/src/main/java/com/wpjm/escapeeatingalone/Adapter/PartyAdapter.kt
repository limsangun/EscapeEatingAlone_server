package com.wpjm.escapeeatingalone.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wpjm.escapeeatingalone.Model.PartyModel
import com.wpjm.escapeeatingalone.R

class PartyAdapter (val partyList: ArrayList<PartyModel>) : RecyclerView.Adapter<PartyAdapter.CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_party, parent, false)
        return CustomViewHolder(view)

    }

    override fun onBindViewHolder(holder: PartyAdapter.CustomViewHolder, position: Int) {
        holder.name.text = partyList.get(position).name
        holder.state.text = partyList.get(position).state
    }

    override fun getItemCount(): Int {
        return partyList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.party_textView_name)
        val state = itemView.findViewById<TextView>(R.id.party_recyclerView)

    }

}