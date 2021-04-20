package com.wpjm.escapeeatingalone.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.wpjm.escapeeatingalone.Model.ChatlistModel
import com.wpjm.escapeeatingalone.Model.PersonModel
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.FragmentPersonBinding

class FragmentChatlistAdapter(val ChatList:ArrayList<ChatlistModel>) : RecyclerView.Adapter<FragmentChatlistAdapter.CustomViewHolder>() {
    private var db = FirebaseFirestore.getInstance()
    private val user = FirebaseAuth.getInstance().currentUser
    var firestore : FirebaseFirestore? = null
    private var mBinding: FragmentPersonBinding? = null
    private val binding get() = mBinding!!

    init {
        db.collection("store")
                .get()
                .addOnSuccessListener { result ->
                    ChatList.clear()
                    for (document in result) {
                        val item = ChatlistModel(document["name"] as String, document["person"] as String, document["date"] as String)
                        ChatList.add(item)


                    }
                    notifyDataSetChanged() // 리사이클러뷰 갱신
                }
                .addOnFailureListener { exception ->
                    Log.e("no data", "$exception")
                }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentChatlistAdapter.CustomViewHolder {
        // item을 붙이기
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chatlist, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ChatList.size
    }
    override fun onBindViewHolder(holder: FragmentChatlistAdapter.CustomViewHolder, position: Int) {


        /*holder.profile.setImageResource(ChatList.get(position).profile)*/
        holder.name.text = ChatList.get(position).name
        holder.person.text = ChatList.get(position).person
        holder.date.text = ChatList.get(position).date
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /*val profile =  itemView.findViewById<ImageView>(R.id.fragmentchatlist_image_profile) // 이미지*/
        val name = itemView.findViewById<TextView>(R.id.fragmentchatlist_textview_name) // 이름
        val person = itemView.findViewById<TextView>(R.id.fragmentchatlist_textview_person) // 숫가락 점수
        val date = itemView.findViewById<TextView>(R.id.fragmentchatlist_textview_date) // 숫가락 점수
    }
}