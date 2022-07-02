package com.lovzoe.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Process
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var logView: TextView

    val connection = object :ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d(TAG, "onServiceConnected")
            log("onServiceConnected, pid = ${Process.myPid()} uid = ${Process.myUid()}")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(TAG, "onServiceDisconnected")
            log("onServiceDisconnected")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logView = findViewById(R.id.log)
//        startService(Intent(this, MyService::class.java))
//        bindService(Intent(this, MyService::class.java))
    }

    private fun bindService(intent: Intent) {
        applicationContext.bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    private fun log(log: String) {
        logView.text = log
    }

    companion object {
        const val TAG = "Service MainActivity"
    }
}