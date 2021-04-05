package com.wpjm.escapeeatingalone.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wpjm.escapeeatingalone.Model.PersonModel
import com.wpjm.escapeeatingalone.R


class FragmentPersonAdapter(val personList:ArrayList<PersonModel>) : RecyclerView.Adapter<FragmentPersonAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentPersonAdapter.CustomViewHolder {
        // item을 붙이기
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return personList.size
    }
    override fun onBindViewHolder(holder: FragmentPersonAdapter.CustomViewHolder, position: Int) {
        holder.profile.setImageResource(personList.get(position).profile)
        holder.name.text = personList.get(position).name
        holder.spoonScore.text = personList.get(position).spoonScore
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profile =  itemView.findViewById<ImageView>(R.id.fragmentperson_image_profile) // 이미지
        val name = itemView.findViewById<TextView>(R.id.fragmentperson_textview_name) // 이름
        val spoonScore = itemView.findViewById<TextView>(R.id.fragmentperson_textview_spoonscore) // 숫가락 점수
    }
}