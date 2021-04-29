package com.wpjm.escapeeatingalone.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.wpjm.escapeeatingalone.Model.PartyModel
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityMenuDetailBinding
import com.wpjm.escapeeatingalone.databinding.ActivityPartyBinding

class PartyActivity : AppCompatActivity() {
    private var mBinding: ActivityPartyBinding? = null
    private val binding get() = mBinding!!
    private var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_party)
        mBinding = ActivityPartyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var storeName = intent.getStringExtra("storeName")
        binding.partyActivityTextViewName.setText(storeName)

        // fireStore 읽어오기
        var partyList = arrayListOf<PartyModel>()
        var adapter = PartyAdapter(partyList)

        db.collection("party")
                .whereEqualTo("storeName", "${storeName}")
                .get()
                .addOnSuccessListener { result ->
                    partyList.clear()
                    for(document in result) {
                        val item = PartyModel(
                                document["date"] as String,
                                document["title"] as String,
                                document["count"] as String,
                                document["storeName"] as String
                        )
                        partyList.add(item)
                    }
                    adapter.notifyDataSetChanged()
                }
                .addOnFailureListener { exception ->
                    Log.e("X", "$exception")
                }

        binding.partyActivityButtonWrite.setOnClickListener {
            gotoActivity(PartyMakeActivity::class.java)
        }

        binding.partyActivityRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.partyActivityRecyclerView.setHasFixedSize(true)
        binding.partyActivityRecyclerView.adapter = adapter
    }

    // Intent function
    private fun gotoActivity(c: Class<*>) {
        var intent = Intent(this, c)
        startActivity(intent)
    }
}