package com.wpjm.escapeeatingalone.Activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityKakaoMapBinding
import net.daum.mf.map.api.MapView
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class MapKakaoActivity : AppCompatActivity() {
    private var mBinding: ActivityKakaoMapBinding? = null
    private val binding get() = mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kakao_map)
        mBinding = ActivityKakaoMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapView = MapView(this)

        val mapViewContainer = binding.mapView
        //mapViewContainer.addView(mapView)

    }

}