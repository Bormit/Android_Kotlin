package ru.mirea.pr46

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val tickReceiver by lazy { makeBroadcastReceiver() }
    private val batteryReceiver by lazy { BatteryLevelReceiver() }

    private var globalTime = Date().time
    private var s = 0

    companion object {
    private fun getTime(): Long {
        val time = Date().time
        return time
    }
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceiver(tickReceiver, IntentFilter(Intent.ACTION_TIME_TICK))

        registerReceiver(batteryReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }
    override fun onDestroy() {
        super.onDestroy()
        try {
            unregisterReceiver(tickReceiver)
            unregisterReceiver(batteryReceiver)
        } catch (e: IllegalArgumentException) {
            Log.e("Broadcast", "Time tick and battery Receiver not registered", e)
        }
    }

    fun cancel_wait(view: View?) {
        onDestroy()
        Toast.makeText(applicationContext, R.string.toast_text, Toast.LENGTH_LONG)
            .show()
    }
    private fun makeBroadcastReceiver(): BroadcastReceiver {
        return object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent?) {
                val textView = findViewById<TextView>(R.id.print)
                if (intent?.action == Intent.ACTION_TIME_TICK) {
                    val time = getTime()
                    var sec = (time.toBigDecimal().div(BigDecimal(1000))).minus(globalTime.toBigDecimal().div(BigDecimal(1000)))
                    if (globalTime < time && sec >= BigDecimal(59)){
                        globalTime = time
                        s += 1
                        textView.setText("время созерцания: " + s + " мин.")
                    }
                    else{
                        globalTime = time
                        textView.setText("время созерцания: " + s + " мин.")
                    }
                }
            }
        }
    }
    private fun BatteryLevelReceiver(): BroadcastReceiver {
        return object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val textView = findViewById<TextView>(R.id.print)
                val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
                    context.registerReceiver(null, ifilter)
                }
                val batteryPct: Float? = batteryStatus?.let { intent ->
                    val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                    val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                    level * 100 / scale.toFloat()
                }
                if (batteryPct?.toBigDecimal()?: BigDecimal.ZERO <= (BigDecimal(15))){
                    try {
                        unregisterReceiver(tickReceiver)
                    } catch (e: IllegalArgumentException) {
                        Log.e("Broadcast", "Time tick not registered", e)
                    }
                    textView.text = resources.getString(R.string.low_batt)
                }
                else{
                    textView.text = resources.getString(R.string.text)
                }
            }
        }
    }
}
