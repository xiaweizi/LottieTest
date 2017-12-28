package com.xiaweizi.lottietest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        refreshing_icon.imageAssetsFolder = "images"
        refreshing_icon.setOnClickListener {
            refreshing_icon.playAnimation()
            refreshing_icon.repeatCount = 1
        }
        heart.progress = 1f
        heart.setOnClickListener {
            heart.playAnimation()
        }
    }
}
