package com.wpjm.escapeeatingalone.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.wpjm.escapeeatingalone.Adapter.BoardAdapter
import com.wpjm.escapeeatingalone.Model.BoardModel
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityBoardBinding

class BoardActivity : AppCompatActivity() {
    private var mBinding: ActivityBoardBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)
        mBinding = ActivityBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val boardList =  arrayListOf(
            BoardModel(R.drawable.android, "고기먹고싶다", "이번주 토요일 아무데나 가실분", "04.02"),
            BoardModel(R.drawable.android, "짬뽕먹고싶다", "배고파 배고파 배고파", "04.02"),
            BoardModel(R.drawable.android, "떡볶이먹고싶다", "죽고싶지만 떡복이는 먹고 싶어", "04.02"),
            BoardModel(R.drawable.android, "볶음밥먹고싶다", "배고파", "04.02"),
            BoardModel(R.drawable.android, "샤브샤브먹고싶다", "중곡동 잘하는 곳 아시는 분", "04.02"),
            BoardModel(R.drawable.android, "포도먹고싶다", "제철임?", "04.02"),
            BoardModel(R.drawable.android, "대게먹고싶다", "대게 대게", "04.02"),
            BoardModel(R.drawable.android, "순대먹고싶다", "배고파 배고파 배고파", "04.02"),
            BoardModel(R.drawable.android, "빵먹고싶다", "배고파", "04.02"),
            BoardModel(R.drawable.android, "크루와상먹고싶다", "배고파 배고파", "04.02"),
            BoardModel(R.drawable.android, "초밥먹고싶다", "배고파", "04.02"),
            BoardModel(R.drawable.android, "불량식품먹고싶다", "배고파 배고파", "04.02"),
            BoardModel(R.drawable.android, "만두먹고싶다", "배고파", "04.02"),
            BoardModel(R.drawable.android, "에이드먹고싶다", "배고파 배고파", "04.02")
        )

        binding.boardActivityRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.boardActivityRecyclerView.setHasFixedSize(true)

        binding.boardActivityRecyclerView.adapter = BoardAdapter(boardList)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater = menuInflater
        inflater.inflate(R.menu.button_ok_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item){

        }

        return super.onOptionsItemSelected(item)
    }
}