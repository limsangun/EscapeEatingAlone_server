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
import com.wpjm.escapeeatingalone.Interface.KakaoAPI
import com.wpjm.escapeeatingalone.Model.Menu
import com.wpjm.escapeeatingalone.Model.MenuDetailModel
import com.wpjm.escapeeatingalone.Model.MenuList
import com.wpjm.escapeeatingalone.Model.ResultSearchKeyword
import com.wpjm.escapeeatingalone.PersonFragment
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityMenuDetailBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import javax.security.auth.callback.Callback

class MenuDetailActivity : AppCompatActivity() {
    private var mBinding: ActivityMenuDetailBinding? = null
    private val binding get() = mBinding!!
    private val listItems = arrayListOf<MenuList>()
    private val listAdapter=MenuDetailAdapter(listItems)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_detail)
        mBinding = ActivityMenuDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var menuType=intent.getStringExtra("menuType")
        var x=intent.getStringExtra("x")
        var y=intent.getStringExtra("y")

        /*
        var filtedMenuDetailList = menuDetailList.filter {
            it.type.equals(intent.getStringExtra("MenuType").toString())
        }
         */

        binding.meniDetailActivityRecyclerView.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.meniDetailActivityRecyclerView.setHasFixedSize(true)
        binding.meniDetailActivityRecyclerView.adapter=listAdapter

        if (menuType != null && x!=null && y!=null) {
            searchKeyword(menuType,x,y)
        }



    }
    // 키워드 검색 함수
    private fun searchKeyword(keyword: String,x:String,y:String) {
        Log.d("searchKeyword","함수시작")
        val retrofit = Retrofit.Builder()          // Retrofit 구성
            .baseUrl(CurrentMapSearchActivity.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(KakaoAPI::class.java)            // 통신 인터페이스를 객체로 생성

        Log.d("call 앞","x는 ${x} y는 ${y}")
        val call = api.getSearchMenu(CurrentMapSearchActivity.API_KEY, keyword,x,y,5000)    // 검색 조건 입력

        // API 서버에 요청
        call.enqueue(object: retrofit2.Callback<ResultSearchKeyword> {
            override fun onResponse(call: Call<ResultSearchKeyword>, response: Response<ResultSearchKeyword>) {
                // 통신 성공
                var searchResult: ResultSearchKeyword?=response.body()
                var menuType:String=keyword
                if (!searchResult?.documents.isNullOrEmpty()) {
                    listItems.clear()
                    // 검색 결과 있음
                    Log.d("검색결과","있음")
                    for (document in searchResult!!.documents) {
                        // 결과를 리사이클러 뷰에 추가
                        val item = MenuList(document.place_name,
                            document.road_address_name,
                            document.address_name,
                            menuType)
                        listItems.add(item)
                        Log.d("검색결과","${listItems[0].name}")
                    }
                    listAdapter.notifyDataSetChanged()
                    Log.d("검색결과 리스트"," 결과는 ? ${listItems[0].name}")
                    Log.d("검색결과 리스트","${listItems[0].menuType}")

                } else {
                    // 검색 결과 없음
                    //Toast.makeText(this, "검색 결과가 없습니다", Toast.LENGTH_SHORT).show()
                }
                Log.d("LocalSearch", "통신 성공")

            }

            override fun onFailure(call: Call<ResultSearchKeyword>, t: Throwable) {
                // 통신 실패
                Log.w("LocalSearch", "통신 실패: ${t.message}")
            }
        })

    }



    override fun onBackPressed() {
        super.onBackPressed()
    }




}