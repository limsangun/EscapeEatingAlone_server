package com.wpjm.escapeeatingalone.Adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.wpjm.escapeeatingalone.Activity.MemberInitActivity
import com.wpjm.escapeeatingalone.Activity.SignupActivity
import com.wpjm.escapeeatingalone.Model.PersonModel
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.FragmentPersonBinding

class FragmentPersonAdapter(val personList:ArrayList<PersonModel>) : RecyclerView.Adapter<FragmentPersonAdapter.CustomViewHolder>() {
    private var db = FirebaseFirestore.getInstance()
    private val user = FirebaseAuth.getInstance().currentUser
    var firestore : FirebaseFirestore? = null
    private var mBinding: FragmentPersonBinding? = null
    private val binding get() = mBinding!!
/*
    init {
        var name=""
        db.collection("users").document(user!!.getUid()).get()
                .addOnSuccessListener { result ->
                    name=result["name"] as String
                    binding.textView2.setText(name)
                }
    }*/

    init {
        db.collection("friends").document(user!!.getUid())
                .get()
                .addOnSuccessListener { result ->
                    Log.d("nullTest!!!!!!!!!!!","일단 success")
                    personList.clear()
                    var fList =result["friendNames"] as MutableList<String>?
                    if (fList != null){
                        Log.d("nullTest!!!!!!!!!!!","리스트에 친구 있음")
                        for (name in fList) {
                            val item = PersonModel(name)
                            personList.add(item)
                        }
                        // 리사이클러뷰 갱신

                    }
                    else{
                        Log.d("nullTest!!!!!!!!!!!","아직 친구없음")

                    }
                    notifyDataSetChanged()
                }
                .addOnFailureListener { exception ->
                    Log.e("no data", "$exception")
                }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentPersonAdapter.CustomViewHolder {
        // item을 붙이기
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return personList.size
    }
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        /*holder.profile.setImageResource(personList.get(position).profile)*/
        holder.name.text = personList.get(position).name
        /*holder.spoonScore.text = personList.get(position).spoonScore*/
    }


    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /*val profile =  itemView.findViewById<ImageView>(R.id.fragmentperson_image_profile) // 이미지*/
        val name = itemView.findViewById<TextView>(R.id.fragmentperson_textview_name) // 이름
        /*val spoonScore = itemView.findViewById<TextView>(R.id.fragmentperson_textview_spoonscore) // 숫가락 점수*/
    }
}