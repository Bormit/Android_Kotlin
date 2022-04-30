package ru.mirea.pr35

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private var changed: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.caption)

        changed = savedInstanceState?.getBoolean("status") ?: false

        if (!changed){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment1, BlankFragmentRed.newInstance())
                .commit()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment2, BlankFragmentBlue.newInstance())
                .commit()
        }
        else{
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment1, BlankFragmentBlue.newInstance())
                .commit()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment2, BlankFragmentRed.newInstance())
                .commit()
        }

        button.setOnClickListener {
            val fragmentManager = supportFragmentManager.beginTransaction()
            changed = if(changed){
                fragmentManager
                    .replace(R.id.fragment1, BlankFragmentRed())

                fragmentManager
                    .replace(R.id.fragment2, BlankFragmentBlue())

                fragmentManager
                    .commit()
                false
            }else{
                fragmentManager
                    .replace(R.id.fragment1, BlankFragmentBlue())

                fragmentManager
                    .replace(R.id.fragment2, BlankFragmentRed())

                fragmentManager
                    .commit()
                true
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("status", changed)
    }
}