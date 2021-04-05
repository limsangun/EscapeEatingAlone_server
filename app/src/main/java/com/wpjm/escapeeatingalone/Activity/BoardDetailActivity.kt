package com.wpjm.escapeeatingalone.Activity

import android.bluetooth.BluetoothAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.wpjm.escapeeatingalone.Adapter.BoardDetailAdapter
import com.wpjm.escapeeatingalone.Model.BoardDetailModel
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityBoardBinding
import com.wpjm.escapeeatingalone.databinding.ActivityBoardDetailBinding

class BoardDetailActivity : AppCompatActivity() {
    private var mBinding: ActivityBoardDetailBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_detail)
        mBinding = ActivityBoardDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 상단 게시글 정보
        Log.e("msg", "${intent.getStringExtra("profile")}")
        var profileNum: Int? = intent.getStringExtra("profile")?.toInt()

        binding.boardDetailImageViewProfile.setImageResource(profileNum!!)
        binding.boardDetailTextViewTitle.text = intent.getStringExtra("title")
        binding.boardDetailTextViewContents.text = intent.getStringExtra("contents")
        binding.boardDetailTextViewDate.text = intent.getStringExtra("date")

        // 댓글 recyclerView
        var commentList = arrayListOf(
            BoardDetailModel(R.drawable.android, "곽진먹", "나도먹고싶다", "04.02"),
            BoardDetailModel(R.drawable.android, "임진먹", "2222", "04.02"),
            BoardDetailModel(R.drawable.android, "윤진먹", "삼합", "04.02")
        )

        binding.boardDetailActivityRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.boardDetailActivityRecyclerView.setHasFixedSize(true)

        binding.boardDetailActivityRecyclerView.adapter = BoardDetailAdapter(commentList)
    }
}