package com.example.lbsapp

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.lbsapp.databinding.ActivityMainBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback{

    private lateinit var activityMainBinding: ActivityMainBinding

    // 승인받을 권한 목록
    val permissionList = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Splash Screen 설정
        installSplashScreen()

        // 권한 승인
        requestPermissions(permissionList, 0)

        // Google Map 라이브러리 초기화
        MapsInitializer.initialize(this@MainActivity, MapsInitializer.Renderer.LATEST, null)
        // Google Map 프래그먼트 동적 추가
        val supportMapFragment = SupportMapFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView2, supportMapFragment).commit()
        // 비동기 처리를 위한 onMapReady 호출
        supportMapFragment.getMapAsync(this)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.run {
            // 확대 축소 버튼 추가
            uiSettings.isZoomControlsEnabled = true

            // 현재 위치 표시 버튼 추가
            if (ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this@MainActivity, permissionList, 0)
            }
            isMyLocationEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }
}