package com.wpjm.escapeeatingalone.Activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityPartyBinding

class PartyActivity : AppCompatActivity() {
    private var mBinding: ActivityPartyBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_party)
        mBinding = ActivityPartyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}