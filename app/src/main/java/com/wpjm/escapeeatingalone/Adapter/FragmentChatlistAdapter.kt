package com.wpjm.escapeeatingalone.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wpjm.escapeeatingalone.Model.ChatlistModel
import com.wpjm.escapeeatingalone.R

class FragmentChatlistAdapter(val ChatList:ArrayList<ChatlistModel>) : RecyclerView.Adapter<FragmentChatlistAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentChatlistAdapter.CustomViewHolder {
        // item을 붙이기
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chatlist, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ChatList.size
    }
    override fun onBindViewHolder(holder: FragmentChatlistAdapter.CustomViewHolder, position: Int) {
        holder.profile.setImageResource(ChatList.get(position).profile)
        holder.name.text = ChatList.get(position).name
        holder.spoonScore.text = ChatList.get(position).person
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profile =  itemView.findViewById<ImageView>(R.id.fragmentchatlist_image_profile) // 이미지
        val name = itemView.findViewById<TextView>(R.id.fragmentchatlist_textview_name) // 이름
        val spoonScore = itemView.findViewById<TextView>(R.id.fragmentchatlist_textview_spoonscore) // 숫가락 점수
    }
}