package com.example.andreapolimena.consulenzeapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import java.util.Calendar;

/**
 * Created by andreapolimena on 13/02/16.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        month++;
        StringBuilder temp = new StringBuilder();
        temp=temp.append(year);
        if(month<10){
            temp.append("-0"+month);
        }else {
            temp.append("-"+month);
        }
        if(day<10){
            temp.append("-0"+day);
        }else {
            temp.append("-"+day);
        }
        ((Button) getActivity().findViewById(R.id.button3)).setText(temp);
    }
}
