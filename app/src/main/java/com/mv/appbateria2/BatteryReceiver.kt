package com.mv.appbateria2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.util.Log

class BatteryReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        Log.d("BatteryReceiver", "Nivel de la batería: $level%")

        val updateIntent = Intent("com.example.UPDATE_BATTERY")
        updateIntent.putExtra("level", level)
        context?.sendBroadcast(updateIntent)
    }
}