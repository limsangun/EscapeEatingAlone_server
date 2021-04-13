package com.wpjm.escapeeatingalone.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wpjm.escapeeatingalone.Adapter.MenuDetailAdapter
import com.wpjm.escapeeatingalone.Model.MenuDetailModel
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityMenuDatailBinding
class MenuDetailActivity : AppCompatActivity() {
    private var mBinding: ActivityMenuDatailBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)
        mBinding = ActivityMenuDatailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val menuList = arrayListOf(
                MenuDetailModel(R.drawable.meat, "원조경성삼겹살", "경성대 탑1 삽겹살집"),
                MenuDetailModel(R.drawable.pork, "푸라삼", "고오급 돼지고기 전문점"),
                MenuDetailModel(R.drawable.pork2, "오구삼", "오븐에 구운 맛있는 삼겹살")
        )
        binding.menuDetailActivityRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.menuDetailActivityRecyclerView.setHasFixedSize(true)
        binding.menuDetailActivityRecyclerView.adapter = MenuDetailAdapter(menuList)
    }
}
