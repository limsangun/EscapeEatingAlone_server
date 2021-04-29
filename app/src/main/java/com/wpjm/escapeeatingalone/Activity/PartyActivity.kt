package com.wpjm.escapeeatingalone.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.wpjm.escapeeatingalone.Model.PartyModel
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityPartyBinding

class PartyActivity : AppCompatActivity() {
    private var mBinding: ActivityPartyBinding? = null
    private val binding get() = mBinding!!
    private var fsdb = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_party)
        mBinding = ActivityPartyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var storeName = intent.getStringExtra("storeName")
        binding.partyActivityTextViewName.setText(storeName)

        var partyList = arrayListOf<PartyModel>()
        var adapter = PartyAdapter(partyList)

       fsdb.collection("party").get()
               .addOnSuccessListener { result ->
                   partyList.clear()
                   for(document in result) {
                       val item = PartyModel(
                               document["date"] as String,
                               document["title"] as String,
                               document["count"] as String
                       )
                       partyList.add(item)
                   }
                   adapter.notifyDataSetChanged()
               }
               .addOnFailureListener { exception -> Log.e("X", "$exception") }

        binding.partyActivityButtonWrite.setOnClickListener (View.OnClickListener {
            gotoActivity(PartyMake::class.java)
        })

        binding.partyActivityRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.partyActivityRecyclerView.setHasFixedSize(true)
        binding.partyActivityRecyclerView.adapter = adapter

    }

    private fun gotoActivity(c: Class<*>) {
        var intent = Intent(this, c)
        startActivity(intent)
    }
}