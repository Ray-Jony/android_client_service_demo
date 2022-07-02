package com.lovzoe.bindertest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var log: TextView

    private val connection =
        object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                log.text = "onServiceConnected"
                Log.d(TAG, "onServiceConnected")
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.d(TAG, "onServiceDisconnected")
                log.text = "onServiceDisconnected"
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        log = findViewById(R.id.log)
        findViewById<Button>(R.id.connect_btn).setOnClickListener {
            bind(Intent("com.lovzoe.service.MY_ACTION").apply {
                setPackage("com.lovzoe.service")
                component = ComponentName("com.lovzoe.service", "com.lovzoe.service.MyService")
            })
        }
    }

    private fun bind(intent: Intent) {
        val component =  baseContext.startService(intent)
        Log.d(TAG, "bind: startService: component = $component")
        val res = baseContext.bindService(intent, connection, Context.BIND_AUTO_CREATE)
        log.text = "bind: res = $res"
        Log.d(TAG, "bind: res = $res")
    }

    companion object {
        const val TAG = "MainActivity"
    }
}