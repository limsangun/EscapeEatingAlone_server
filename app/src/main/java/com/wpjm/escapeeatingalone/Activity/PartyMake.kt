package com.wpjm.escapeeatingalone.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.wpjm.escapeeatingalone.Model.PartyModel
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityPartyMakeBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class PartyMake : AppCompatActivity() {
    private var mBinding : ActivityPartyMakeBinding ?= null
    private val binding get() = mBinding!!
    private var fsdb = FirebaseFirestore.getInstance()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_party_make)
        mBinding = ActivityPartyMakeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var title = binding.partyMakeWrite.getText().toString()

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MM/dd")
        val timeStamp = current.format(formatter)

        binding.partyMakeButtonComplete.setOnClickListener(View.OnClickListener {
            if (binding.partyMakeWrite.getText().toString().length > 0) {
                var partyModel = PartyModel( binding.partyMakeWrite.getText().toString(), timeStamp.toString(), toString())

                fsdb.collection("party").document(timeStamp.toString()).set(partyModel)
                        .addOnCanceledListener {
                            Toast.makeText(this, "등록 성공", Toast.LENGTH_SHORT).show()
                            gotoActivity(PartyActivity::class.java)
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "등록 실패", Toast.LENGTH_SHORT).show()
                        }
            }
            else {
                Toast.makeText(this, "모집글을 써주세요.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun gotoActivity(c : Class<*>) {
        var intent = Intent(this, c)
        startActivity(intent)
    }

}


