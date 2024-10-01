package com.mv.appbateria2

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mv.appbateria2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var texto: TextView
    private lateinit var pendingIntent: PendingIntent
    private lateinit var batteryReceiver: BroadcastReceiver

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        texto = binding.txtNivel
        //batteryReceiver = BatteryReceiver()

        // Crea un Intent para BatteryReceiver
        val batteryIntent = Intent(this, BatteryReceiver::class.java)

        // Crea un PendingIntent que será usado para lanzar el BroadcastReceiver
        pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            batteryIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Registrar el Broadcast para actualizar el nivel de la batería
        batteryReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
                texto.text = "$level%"  // Display battery level
                Log.d("MainActivity", "Nivel de batería: $level%")
            }
        }

        // Registra el receptor para ACTION_BATTERY_CHANGED
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryReceiver, intentFilter)

    }

    override fun onResume() {
        super.onResume()
        try {
            pendingIntent.send()
            Log.d("MainActivity","BroadcastReceiver registrado satisfactoriamente")
        } catch (e: PendingIntent.CanceledException) {
            Log.e("MainActivity", "Error al enviar PendingIntent: ${e.message}")
        }
    }

    override fun onPause() {
        super.onPause()
        // Desregistrar el receptor cuando la app no está visible
        unregisterReceiver(batteryReceiver)
        Log.d("MainActivity", "BroadcastReceiver desregistrado satisfactoriamente")
    }
}