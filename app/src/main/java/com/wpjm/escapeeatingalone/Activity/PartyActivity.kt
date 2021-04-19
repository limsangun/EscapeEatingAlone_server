package com.wpjm.escapeeatingalone.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.wpjm.escapeeatingalone.Model.PartyModel
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityMenuDetailBinding
import com.wpjm.escapeeatingalone.databinding.ActivityPartyBinding

class PartyActivity : AppCompatActivity() {
    private var mBinding: ActivityPartyBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_party)
        mBinding = ActivityPartyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var storeName = intent.getStringExtra("storeName")
        binding.partyActivityTextViewName.setText(storeName)

        var partyList = arrayListOf(
            PartyModel("4/17", "빨리갈 친구들 구함", "1/4"),
            PartyModel("4/17", "빨리갈 친구들 구함", "1/4"),
            PartyModel("4/17", "빨리갈 친구들 구함", "1/4"),
            PartyModel("4/17", "빨리갈 친구들 구함", "1/4"),
            PartyModel("4/17", "빨리갈 친구들 구함", "1/4")
        )

        binding.partyActivityRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.partyActivityRecyclerView.setHasFixedSize(true)
        binding.partyActivityRecyclerView.adapter = PartyAdapter(partyList)

    }
}