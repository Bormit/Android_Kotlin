package ru.mirea.pr37

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class TestCheckboxDialog(val onOkey: ((BooleanArray)-> Unit)): DialogFragment() {
    val array = arrayOf("Луна и звезды ночью", "Солнце днем")
    val arrayChecked: BooleanArray = booleanArrayOf(true,true)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            AlertDialog.Builder(it)
                .setMultiChoiceItems(array, arrayChecked){ _, which, isChecked->
                    arrayChecked[which] = isChecked
                }
                .setPositiveButton("Ok"
                ) { _, _ ->
                    onOkey.invoke(arrayChecked)
                }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}



//https://www.android--code.com/2018/04/android-kotlin-alertdialog-multiple.html