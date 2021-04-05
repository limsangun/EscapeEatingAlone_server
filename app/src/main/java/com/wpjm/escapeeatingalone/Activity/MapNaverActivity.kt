package com.wpjm.escapeeatingalone.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.wpjm.escapeeatingalone.R

class MapNaverActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_naver)

        mapView = findViewById(R.id.map_view)

        mapView.getMapAsync(this)

    }

    override fun onMapReady(naverMap: NaverMap) {
        val marker = Marker()
        marker.position = LatLng(37.5670135, 126.9783740)
        marker.map = naverMap
    }
}