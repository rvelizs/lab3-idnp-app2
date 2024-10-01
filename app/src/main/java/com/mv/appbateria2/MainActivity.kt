package com.mv.appbateria2

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var texto: TextView
    private lateinit var batteryReceiver: BatteryReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        texto = findViewById(R.id.txtNivel)
        batteryReceiver = BatteryReceiver()

        // Crea un Intent para BatteryReceiver
        val batteryIntent = Intent(this, BatteryReceiver::class.java)

        // Crea un PendingIntent que será usado para lanzar el BroadcastReceiver
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            batteryIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

    }
}