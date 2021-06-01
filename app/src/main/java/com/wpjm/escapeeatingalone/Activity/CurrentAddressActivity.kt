package com.wpjm.escapeeatingalone.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityChatBinding
import com.wpjm.escapeeatingalone.databinding.ActivityCurrentAddressBinding
import com.wpjm.escapeeatingalone.databinding.ActivityLoginBinding

class CurrentAddressActivity : AppCompatActivity() {
    private var mBinding: ActivityCurrentAddressBinding? = null
    private val binding get() = mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_address)
        mBinding = ActivityCurrentAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.currentLotationBtn.setOnClickListener {
            gotoActivity(CurrentMapSearchActivity::class.java)
        }
    }
    private fun gotoActivity(c: Class<*>) {
        var intent = Intent(this, c)
        startActivity(intent)

    }
}