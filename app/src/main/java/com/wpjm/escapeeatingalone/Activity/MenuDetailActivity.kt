package com.wpjm.escapeeatingalone.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.wpjm.escapeeatingalone.Model.Menu
import com.wpjm.escapeeatingalone.Model.MenuDetailModel
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityMenuDetailBinding

class MenuDetailActivity : AppCompatActivity() {
    private var mBinding: ActivityMenuDetailBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_detail)
        mBinding = ActivityMenuDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var menuDetailList= arrayListOf(
            MenuDetailModel(R.drawable.rice, "우쭈쭈", "경성대 쭈꾸미집","한식"),
            MenuDetailModel(R.drawable.rice, "미진축산", "경성대 삼겹살집", "한식"),
            MenuDetailModel(R.drawable.rice, "영진돼지국밥", "경성대 돼지국밥", "한식"),
            MenuDetailModel(R.drawable.rice, "차이나타운", "경성대 중국집", "중식"),
            MenuDetailModel(R.drawable.rice, "겐로쿠", "경성대 우동", "일식")
        )

        binding.meniDetailActivityRecyclerView.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.meniDetailActivityRecyclerView.setHasFixedSize(true)
        binding.meniDetailActivityRecyclerView.adapter=MenuDetailAdapter(menuDetailList)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}