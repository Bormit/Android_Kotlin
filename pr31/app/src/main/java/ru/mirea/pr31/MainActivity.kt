package ru.mirea.pr31

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val StartingRange: EditText = findViewById(R.id.StartingRange)
        val EndRange: EditText = findViewById(R.id.EndRange)
        val EnterButton: Button = findViewById(R.id.EnterButton)

        EnterButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AnswerActivity::class.java)
            intent.putExtra("start", StartingRange.text.toString())
            intent.putExtra("end", EndRange.text.toString())
            startActivity(intent)
        }
    }
}