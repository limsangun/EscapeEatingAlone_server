package com.wpjm.escapeeatingalone.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wpjm.escapeeatingalone.Model.BoardDetailModel
import com.wpjm.escapeeatingalone.R


class BoardDetailAdapter(val BoardCommentList:ArrayList<BoardDetailModel>) : RecyclerView.Adapter<BoardDetailAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardDetailAdapter.CustomViewHolder {
        // item을 붙이기
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_boardcomment, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  BoardCommentList.size
    }

    override fun onBindViewHolder(holder: BoardDetailAdapter.CustomViewHolder, position: Int) {
        holder.profile.setImageResource(BoardCommentList.get(position).profile)
        holder.name.text = BoardCommentList.get(position).name
        holder.contents.text = BoardCommentList.get(position).contents
        holder.date.text = BoardCommentList.get(position).date
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profile =  itemView.findViewById<ImageView>(R.id.boardComment_image_profile) // 이미지
        val name = itemView.findViewById<TextView>(R.id.boardComment_textview_name) // 이름
        val contents = itemView.findViewById<TextView>(R.id.boardComment_textview_contents) // 내용
        val date = itemView.findViewById<TextView>(R.id.boardComment_textview_date) // 날짜
    }
}