package com.wpjm.escapeeatingalone.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wpjm.escapeeatingalone.Adapter.ListAdapter
import com.wpjm.escapeeatingalone.Interface.KakaoAPI
import com.wpjm.escapeeatingalone.Model.Menu
import com.wpjm.escapeeatingalone.Model.MenuList
import com.wpjm.escapeeatingalone.Model.ResultSearchKeyword
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityMenulistBinding
import net.daum.mf.map.api.MapPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MenuActivity() : AppCompatActivity() {
    private var mBinding: ActivityMenulistBinding?=null
    private val binding get() = mBinding!!
    private val ACCESS_FINE_LOCATION = 1000
    private val listItems = arrayListOf<MenuList>()   //아이템 리스트
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menulist)
        mBinding = ActivityMenulistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val x=intent.getStringExtra("x")
        val y=intent.getStringExtra("y")
        Log.d("좌표","${x} 와 ${y}")
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
        val listAdapter = MenuAdapter(menuList)
        binding.rvMenu.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.rvMenu.setHasFixedSize(true)
        binding.rvMenu.adapter=listAdapter
        var intent = Intent(this, MenuDetailActivity::class.java)
        listAdapter.setItemClickListener(object: MenuAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                Log.d("메뉴 클릭","클릭 ${menuList[position].name}")
                var keyword=menuList[position].name
                intent.putExtra("menuType",menuList[position].name)
                intent.putExtra("x",x)
                intent.putExtra("y",y)
                startActivity(intent)


            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}