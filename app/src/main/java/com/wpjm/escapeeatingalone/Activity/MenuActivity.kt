package com.wpjm.escapeeatingalone.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.wpjm.escapeeatingalone.Model.Menu
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityMenulistBinding

class MenuActivity() : AppCompatActivity() {
    private var mBinding: ActivityMenulistBinding?=null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menulist)
        mBinding = ActivityMenulistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var menuList= arrayListOf(
                Menu("한식",R.drawable.rice),
                Menu("일식",R.drawable.sushi),
                Menu("중식",R.drawable.chinafood),
                Menu("치킨",R.drawable.chicken),
                Menu("피자",R.drawable.pizza),
                Menu("분식",R.drawable.fishcake),
                Menu("패스트푸드",R.drawable.fastfood),
                Menu("카페",R.drawable.cupcake)
        )

        binding.rvMenu.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.rvMenu.setHasFixedSize(true)
        binding.rvMenu.adapter=MenuAdapter(menuList)
    }


    override fun onBackPressed() {
        super.onBackPressed()
    }
}