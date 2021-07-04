package com.zkqueen.test

import android.app.Application
import android.util.Log
import com.tencent.mmkv.MMKV
import com.tencent.mmkv.MMKVHandler
import com.tencent.mmkv.MMKVLogLevel
import com.tencent.mmkv.MMKVRecoverStrategic

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 初始化mmkv
        val rootDir = MMKV.initialize(this)
        MMKV.registerHandler(object : MMKVHandler {
            override fun onMMKVCRCCheckFail(s: String): MMKVRecoverStrategic? {
                return null
            }

            override fun onMMKVFileLengthError(s: String): MMKVRecoverStrategic? {
                return null
            }

            override fun wantLogRedirecting(): Boolean {
                return false
            }

            override fun mmkvLog(
                mmkvLogLevel: MMKVLogLevel,
                s: String,
                i: Int,
                s1: String,
                s2: String
            ) {
                Log.d(TAG, "mmkvLog: s: $s -- i: $i -- s1 :$s1 -- s2: $s2")
            }
        })
        Log.d(TAG, "onCreate: $rootDir")
    }

    companion object {
        private const val TAG = "MyApplication"
    }
}