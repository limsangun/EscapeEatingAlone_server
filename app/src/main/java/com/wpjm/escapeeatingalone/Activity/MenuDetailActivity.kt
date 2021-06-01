package com.wpjm.escapeeatingalone.Activity

import android.app.ActivityManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wpjm.escapeeatingalone.Model.Menu
import com.wpjm.escapeeatingalone.Model.MenuDetailModel
import com.wpjm.escapeeatingalone.Model.MenuList
import com.wpjm.escapeeatingalone.PersonFragment
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityMenuDetailBinding
import java.lang.Exception
import javax.security.auth.callback.Callback

class MenuDetailActivity : AppCompatActivity() {
    private var mBinding: ActivityMenuDetailBinding? = null
    private val binding get() = mBinding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_detail)
        mBinding = ActivityMenuDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var list=intent.getSerializableExtra("resultList") as ArrayList<MenuList>
        var listAdapter=MenuDetailAdapter(list)
        Log.d("리스트 테스트","테스트 데이터 : ${list}")
        /*
        var filtedMenuDetailList = menuDetailList.filter {
            it.type.equals(intent.getStringExtra("MenuType").toString())
        }
         */


        binding.meniDetailActivityRecyclerView.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.meniDetailActivityRecyclerView.setHasFixedSize(true)
        binding.meniDetailActivityRecyclerView.adapter=listAdapter
        listAdapter.notifyDataSetChanged()



    }



    override fun onBackPressed() {
        super.onBackPressed()
    }




}