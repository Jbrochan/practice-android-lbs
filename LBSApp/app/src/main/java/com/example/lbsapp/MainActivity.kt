package com.example.lbsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.lbsapp.databinding.ActivityMainBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.UiSettings

class MainActivity : AppCompatActivity(), OnMapReadyCallback{

    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()


        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


        // Google Map Option 설정
        val options = GoogleMapOptions()
        // 확대 축소 버튼 구현
        options.zoomControlsEnabled(true)

        // Google Map 프래그먼트 동적 추가
        val mapFragment = SupportMapFragment.newInstance(options)
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView2, mapFragment).commit()
    }

    override fun onMapReady(googleMap: GoogleMap) {
    }
}