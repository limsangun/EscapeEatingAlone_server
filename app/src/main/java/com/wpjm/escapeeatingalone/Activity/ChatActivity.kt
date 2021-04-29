package com.wpjm.escapeeatingalone.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wpjm.escapeeatingalone.ChatlistFragment
import com.wpjm.escapeeatingalone.PersonFragment
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {
    private var mBinding: ActivityChatBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        mBinding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFrag(0)
        binding.btnFragment1.setOnClickListener {
            setFrag(0)
        }
        binding.btnFragment2.setOnClickListener {
            setFrag(1)
        }
    }

    fun setFrag(fragNum : Int) {
        val ft = supportFragmentManager.beginTransaction()
        when(fragNum)
        {
            0 -> {
                ft.replace(R.id.main_frame, PersonFragment()).commit()
            }
            1 -> {
                ft.replace(R.id.main_frame, ChatlistFragment()).commit()
            }
        }
    }

}