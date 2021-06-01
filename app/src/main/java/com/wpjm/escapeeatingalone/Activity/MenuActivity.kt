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
    companion object {
        const val BASE_URL = "https://dapi.kakao.com/"
        const val API_KEY = "KakaoAK 24332e6fac5132ea5dc1a303bee1707e"  // REST API 키
    }
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
        listAdapter.setItemClickListener(object: MenuAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                Log.d("메뉴 클릭","클릭 ${menuList[position].name}")
                var keyword=menuList[position].name
                if (x != null && y!=null) {
                    searchKeyword(keyword,x,y)
                }

            }
        })
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
        call.enqueue(object: Callback<ResultSearchKeyword> {
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

        var intent = Intent(this, MenuDetailActivity::class.java)
        intent.putExtra("resultList",listItems)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}