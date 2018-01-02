package com.xiaweizi.lottietest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var pullUtil = PullToRefreshUtil(refresh_pull)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                if (p0!!.progress >= 100) {
                    pullUtil.refreshLoading()
                }
            }

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                pullUtil.setRefreshProgress(p1 * 0.01f)
            }

        })

        reset.setOnClickListener{
            pullUtil.refreshReset()
        }

        loading.setOnClickListener {
            pullUtil.refreshLoading()
        }
    }
}
