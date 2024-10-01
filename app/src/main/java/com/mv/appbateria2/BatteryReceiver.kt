package com.mv.appbateria2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.util.Log

class BatteryReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // Obtener el nivel de la batería desde BatteryManager
        val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1

        // Crear un Intent personalizado para enviar el nivel de batería
        val batteryLevelIntent = Intent("com.mv.appbateria2.BATTERY_LEVEL")
        batteryLevelIntent.putExtra("level", level)

        // Enviar el broadcast
        context?.sendBroadcast(batteryLevelIntent)

        Log.d("BatteryReceiver", "$level%")
    }
}