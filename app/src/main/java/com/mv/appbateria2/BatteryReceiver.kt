package com.mv.appbateria2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.util.Log

class BatteryReceiver(private val onBatteryLevelChanged: (Int) -> Unit) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        Log.d("Battery Receiver", "Nivel de batería: $level%")
        onBatteryLevelChanged(level)
    }
}