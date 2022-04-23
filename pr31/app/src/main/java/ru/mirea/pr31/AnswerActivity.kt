package ru.mirea.pr31

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AnswerActivity : Activity() {
    var start = 0
    var end = 0
    var newHalf= 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main1)

        val textView: TextView = findViewById(R.id.textView)
        val Larger: Button = findViewById(R.id.Larger)
        val Less: Button = findViewById(R.id.Less)

        try {
            start = (intent.extras?.getString("start"))!!.toInt()
            end = intent.extras?.getString("end")!!.toInt()
            if (end > start) {
                newHalf = getHalf()
                if (getLength() != 2) {
                    textView.setText("Ваше число: " + newHalf + "?")
                }
                else {
                    textView.setText("Ваше число: " + start + "?")
                }
            }
            else {
                textView.setText("Ошибка ввода")
            }
        }
        catch (e: Exception){
            textView.setText("Ошибка ввода")
        }

        if (end > start) {
            Larger.setOnClickListener() {
                start = newHalf
                newHalf = getHalf()
                if (getLength() != 2) {
                    textView.setText("Ваше число: " + newHalf + "?")
                }
                else {
                    textView.setText("Ваше число: " + end + "?")
                }

            }
        }
        else {
            textView.setText("Ошибка ввода")
        }

        if (end > start) {
            Less.setOnClickListener {
                end = newHalf
                newHalf = getHalf()
                if (getLength() != 2) {
                    textView.setText("Ваше число: " + newHalf + "?")
                }
                else {
                    textView.setText("Ваше число: " + start + "?")
                }
            }
        }
        else {
            textView.setText("Ошибка ввода")
        }

    }
    fun getHalf(): Int {
        val length = (end - start) + 1
        val half = (length / 2) + start
        return half
    }
    fun getLength(): Int {
        val length = (end - start) + 1
        return length
    }
}