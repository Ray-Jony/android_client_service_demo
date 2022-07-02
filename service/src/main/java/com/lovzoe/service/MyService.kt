package com.lovzoe.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.Parcel
import android.os.Process
import android.util.Log

class MyService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand  pid = ${Process.myPid()} uid = ${Process.myUid()}")
        return START_STICKY
    }

    private val binder = object : Binder() {
        override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {
            Log.d(TAG, "onTransact")
            return true
        }
    }
    
    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind  pid = ${Process.myPid()} uid = ${Process.myUid()}")

        return binder
    }

    companion object {
        const val TAG = "MyService"
    }
}