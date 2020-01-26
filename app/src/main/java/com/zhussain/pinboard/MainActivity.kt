package com.zhussain.pinboard


import android.app.ActivityManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityManagerCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var activityManager  = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        //var availMemoryInBytes = activityManager.memoryClass*1024*1024



    }
}
