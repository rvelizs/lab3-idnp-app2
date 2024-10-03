package com.mv.appbateria2

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mv.appbateria2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var batteryReceiver: BatteryReceiver
    private lateinit var binding: ActivityMainBinding
    lateinit var texto: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        batteryReceiver = BatteryReceiver { batteryLevel ->
            // Actualizar el TextView cuando se recibe el nivel de la batería
            texto.text = "$batteryLevel%"
        }

        val view: View
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view)

        // Asigna el TextView para mostrar el valor
        texto = binding.txtNivel
    }

    override fun onResume() {
        super.onResume()
        // Crear un Intent para recibir el estado de la batería
        val intent = Intent(this, BatteryReceiver::class.java)

        // Crear un PendingIntent que envía la transmisión a BateryReceiver
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryReceiver, intentFilter)

        Log.d("Aplicación", "PendingIntent para BroadcastReceiver registrado satisfactoriamente")
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(batteryReceiver)
        Log.d("Aplicación", "PendingIntent para BroadcastReceiver desregistrado satisfactoriamente")
    }
}