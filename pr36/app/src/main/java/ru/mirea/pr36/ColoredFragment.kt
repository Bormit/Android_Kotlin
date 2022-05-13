package ru.mirea.pr36

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import java.lang.NullPointerException


class ColoredFragment : Fragment() {
    private var pos: Int = 0
    private val SOME_VALUE_KEY = "myfragmenttag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(SOME_VALUE_KEY, pos)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_colored, container, false) //получаем разметку colored
        if (savedInstanceState != null) {
            pos = savedInstanceState.getInt(SOME_VALUE_KEY);
            // Do something with value if needed
        }
        val textView = view.findViewById<TextView>(R.id.color) // получаем элемент textView из view

        val argument = this.arguments
        pos = try {
            argument?.getInt("index")!!
        }catch (e: NullPointerException){
            130
        }

        textView.setBackgroundColor(getColorValues()[pos])


        return view
    }
    private fun getColorValues() = resources.getIntArray(R.array.colorValues)


    companion object {
        @JvmStatic
        fun newInstance() =
            ColoredFragment().apply {}
    }
}

//https://www.techiedelight.com/convert-nullable-int-to-int-kotlin/