package ru.mirea.pr49

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import androidx.work.*

class MainActivity : AppCompatActivity() {
    private var count: Int = 0

//Данные помещаем в объект Data с помощью его билдера.
    // Далее этот объект передаем в метод setInputData билдера WorkRequest.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val startprocess = findViewById<Button>(R.id.startprocess)
        val clicker = findViewById<Button>(R.id.clicker)

        startprocess.setOnClickListener {
            var process1 = OneTimeWorkRequest.Builder(TextWorker::class.java)
                .addTag("process1").build()
//            WorkManager.getInstance(this).enqueue(process1) //вызов workManager
            var process2 = OneTimeWorkRequest.Builder(LongWorker::class.java)
                .setInputData(data.invoke())
                .addTag("process2").build()
//            WorkManager.getInstance(this).enqueue(process2) //вызов workManager
            WorkManager.getInstance(this) //вызов workManager последовательно
                .beginWith(process1)
                .then(process2)
                .enqueue();
        }
        clicker.setOnClickListener {
            count++
            clicker.text = count.toString()
        }

    }
    private val data = { Data.Builder()
        .putInt("count", count)
        .build() }

    override fun onDestroy() {
        super.onDestroy()
        WorkManager.getInstance().cancelAllWork()
    }
}

//https://startandroid.ru/ru/courses/architecture-components/27-course/architecture-components/565-urok-32-workmanager-peredacha-i-poluchenie-dannyh.html