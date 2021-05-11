package com.wpjm.escapeeatingalone.Adapter

import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import com.wpjm.escapeeatingalone.Model.PersonModel
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.FragmentPersonBinding


class FragmentPersonAdapter(val personList: ArrayList<PersonModel>) : RecyclerView.Adapter<FragmentPersonAdapter.CustomViewHolder>() {
    private var db = FirebaseFirestore.getInstance()
    private val user = FirebaseAuth.getInstance().currentUser
    var firestore : FirebaseFirestore? = null
    private var mBinding: FragmentPersonBinding? = null
    private val binding get() = mBinding!!
    private val uidList: List<String> = ArrayList()
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
                    Log.d("nullTest!!!!!!!!!!!", "일단 success")
                    personList.clear()
                    var fList =result["friendNames"] as MutableList<String>?
                    if (fList != null){
                        Log.d("nullTest!!!!!!!!!!!", "리스트에 친구 있음")
                        for (name in fList) {
                            val item = PersonModel(name)
                            personList.add(item)
                        }
                    }
                    else{
                        Log.d("nullTest!!!!!!!!!!!", "아직 친구없음")
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

        /*view.findViewById<Button>(R.id.fragmentperson_button_delete).setOnClickListener {
            val myDatabase = FirebaseDatabase.getInstance().getReference("friends")
            myDatabase.child("friendNames").removeValue()
            /*db.collection("friends").document(user!!.getUid())
                    .delete()
                    .addOnSuccessListener { Log.e("성공", "삭제") }
                    .addOnFailureListener { e -> Log.e("실패", "Error deleting document", e) }

            db.collection("friends").document(user!!.getUid())
            .get()
            .addOnSuccessListener { result ->
                personList.clear()
                var fList =result["friendNames"] as MutableList<String>
                for (name in fList) {
                    val item = PersonModel(name)
                    personList.add(item)
                }
                personList.removeAt(1)
                fList.removeAt(1)
                Log.e("성공", "삭제")
                notifyDataSetChanged() // 리사이클러뷰 갱신
            }
            .addOnFailureListener { exception ->
                Log.e("no data", "$exception")
            }*/

        }*/
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