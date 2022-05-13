package ru.mirea.pr36

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private var fragment1: ColoredFragment? = null
    private val SIMPLE_FRAGMENT_TAG = "ADD_COLORED_FRAGMENT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) { // saved instance state, fragment1 may exist
            // look up the instance that already exists by tag
            fragment1 =
                supportFragmentManager.findFragmentByTag(SIMPLE_FRAGMENT_TAG) as ColoredFragment?
        } else if (fragment1 == null) {
            // only create fragment1 if they haven't been instantiated already
            fragment1 = ColoredFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment1, ColorListFragment.newInstance(), "ADD_COLOR_LIST")
                .commit()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment2, ColoredFragment.newInstance(), "ADD_COLORED_FRAGMENT")
                .commit()
        }
    }
}


//https://riptutorial.com/android/example/16231/saving-and-restoring-fragment-state