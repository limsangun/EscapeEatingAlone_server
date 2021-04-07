package com.wpjm.escapeeatingalone.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityBoardBinding
import com.wpjm.escapeeatingalone.databinding.ActivityBoardMakeBinding

class BoardMake : AppCompatActivity() {
    private var mBinding: ActivityBoardMakeBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_make)
        mBinding = ActivityBoardMakeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 확인버튼을 눌렀을 때
        binding.boardMakeButtonOk.setOnClickListener(View.OnClickListener {
            Log.e("title", binding.boardMakeEdittextTitle.toString())
            Log.e("cotents", binding.boardMakeEdittextContents.toString())
        })
    }

}