package ru.mirea.pr37

import android.app.Dialog
import android.widget.TimePicker

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*


class TestTimePickerDialog(val dialogTime: ((Int, Int)-> Unit)): DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c = Calendar.getInstance();
        val curHour = c.get(Calendar.HOUR_OF_DAY);
        val curMinute = c.get(Calendar.MINUTE);

        return TimePickerDialog(activity, {
                _, hour, minute ->
            dialogTime.invoke(hour, minute)
        }, curHour, curMinute, false)
    }
}