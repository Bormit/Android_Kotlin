package ru.mirea.pr37

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private var arrayChecked: BooleanArray = booleanArrayOf(true,true)
    var fragment: TestFragment.TestView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragment = findViewById(R.id.fragment)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        return when (event.action) {

            MotionEvent.ACTION_UP -> {
                val checkboxDialog = TestCheckboxDialog(dialogBoxOkey)
                checkboxDialog.show(supportFragmentManager, "custom")
                true
            }

            else ->
                false
        }
    }
    private val dialogBoxOkey: (BooleanArray)-> Unit = { arrayChecked: BooleanArray -> //создание функции с одним параметром
        this.arrayChecked = arrayChecked
        val testTimeDialog = TestTimePickerDialog(dialogTime)
        testTimeDialog.show(supportFragmentManager, "custom2")
    }
    private val dialogTime: (Int, Int)-> Unit = { hour: Int, minute: Int -> //создание функции с двумя параметром
        fragment!!.setState(hour, minute, arrayChecked[1], arrayChecked[0])
    }
}