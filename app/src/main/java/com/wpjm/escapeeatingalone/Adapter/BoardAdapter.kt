package com.wpjm.escapeeatingalone.Adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.wpjm.escapeeatingalone.Activity.BoardDetailActivity
import com.wpjm.escapeeatingalone.Model.BoardModel
import com.wpjm.escapeeatingalone.R


class BoardAdapter(val BoardList: ArrayList<BoardModel>) : RecyclerView.Adapter<BoardAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardAdapter.CustomViewHolder {
        // item을 붙이기
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_board, parent, false)
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener {
                View.OnClickListener {
                    var currentPosition: Int = adapterPosition
                    val currentBoard: BoardModel = BoardList.get(currentPosition)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return BoardList.size
    }

    override fun onBindViewHolder(holder: BoardAdapter.CustomViewHolder, position: Int) {
        holder.profile.setImageResource(BoardList.get(position).profile)
        holder.title.text = BoardList.get(position).title
        holder.contents.text = BoardList.get(position).contents
        holder.date.text = BoardList.get(position).date


        // 리스트 눌렀을 때
        holder.itemView.setOnClickListener {
            var intent = Intent(holder.itemView?.context, BoardDetailActivity::class.java)
            intent.putExtra("profile", "${BoardList.get(position).profile.toString()}")
            intent.putExtra("title", "${holder.title.text}")
            intent.putExtra("contents", "${holder.contents.text}")
            intent.putExtra("date", "${holder.date.text}")
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }


    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profile = itemView.findViewById<ImageView>(R.id.boardActivity_image_profile) // 이미지
        val title = itemView.findViewById<TextView>(R.id.boardActivity_textview_title) // 제목
        val contents = itemView.findViewById<TextView>(R.id.boardActivity_textview_contents) // 내용
        val date = itemView.findViewById<TextView>(R.id.boardActivity_textview_date) // 날짜

    }
}