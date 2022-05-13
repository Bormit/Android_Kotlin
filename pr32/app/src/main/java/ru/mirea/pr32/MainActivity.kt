package ru.mirea.pr32

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val editText: EditText = findViewById(R.id.editText)
        val button: Button = findViewById(R.id.button)
        val RadioGroup: RadioGroup = findViewById(R.id.RadioGroup)

        button.setOnClickListener {
            val choise = RadioGroup.checkedRadioButtonId
            when (choise) {
                R.id.radio_website -> {
                    val website: String = "https://${editText.text.toString().trim()}"
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(website)))
                }
                R.id.radio_geo -> {
                    try {
                        val geo = editText.text.toString().split(",").toTypedArray()
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:${geo[0]},${geo[1]}")))
                    } catch (e: Exception){
                        Toast.makeText(applicationContext, "Неправильный ввод!", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.radio_phone -> {
                    try {
                        val phone: Int = editText.text.toString().toInt()
                        startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone")))
                    } catch (e: Exception){
                        Toast.makeText(applicationContext, "Неправильный ввод!", Toast.LENGTH_SHORT).show()
                    }
                }
                else -> {
                    try {

                        val phone: Int = editText.text.toString().toInt()
                        startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone")))
                    } catch (e: NumberFormatException){
                        try {
                            val geo = editText.text.toString().split(",").toTypedArray()
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:${geo[0]},${geo[1]}")))
                        } catch (e: ArrayIndexOutOfBoundsException) {
                            if (editText.text.isNotEmpty()) {
                                val website: String = "https://${editText.text.toString().trim()}"
                                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(website)))
                            }
                            else
                                Toast.makeText(applicationContext, "Неправильный ввод!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}
