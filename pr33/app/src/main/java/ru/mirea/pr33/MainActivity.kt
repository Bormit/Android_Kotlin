package ru.mirea.pr33

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var data = getList(applicationContext)

        val sortByName = findViewById<Button>(R.id.sortByName)
        val sortBySex = findViewById<Button>(R.id.sortBySex)
        val sortByPhone = findViewById<Button>(R.id.sortByPhone)

        var nameSortByASC: Boolean = true
        var sexSortByASC: Boolean = true
        var phoneSortByASC: Boolean = true

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.layoutManager = LinearLayoutManager(this) //соединение с layout и передача данных в adapter

        val adapter = CustomAdapter(data)
        recyclerview.adapter = adapter

        sortByName.setOnClickListener {
            if (nameSortByASC) {
                data = data.sortedBy { it.name }
                nameSortByASC = false
            }
            else {
                data = data.sortedBy { it.name }.reversed()
                nameSortByASC = true
            }
            val adapter = CustomAdapter(data)
            recyclerview.adapter = adapter
        }

        sortBySex.setOnClickListener {
            if (sexSortByASC) {
                data = data.sortedBy { it.sex }
                sexSortByASC = false
            }
            else{
                data = data.sortedBy { it.sex }.reversed()
                sexSortByASC = true
            }

            val adapter = CustomAdapter(data)
            recyclerview.adapter = adapter
        }

        sortByPhone.setOnClickListener {
            if (phoneSortByASC){
                data = data.sortedBy { it.phoneNumber }
                phoneSortByASC = false
            }
            else{
                data = data.sortedBy { it.phoneNumber }.reversed()
                phoneSortByASC = true
            }

            val adapter = CustomAdapter(data)
            recyclerview.adapter = adapter
        }
    }
    fun getList(context: Context): List<Person> {
        lateinit var jsonString: String

        try {
            jsonString = context.assets.open("input.json")
                .bufferedReader()
                .use{
                    it.readText()// открывает и читает файл
                }

        } catch (e: IOException){
            Toast.makeText(applicationContext, "Неправильный ввод!", Toast.LENGTH_SHORT).show()
        }
        val list = object : TypeToken<List<Person>>() {}.type //преобразование из json в list n
        return Gson().fromJson(jsonString, list)
    }
}