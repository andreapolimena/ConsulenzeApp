package com.example.andreapolimena.consulenzeapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by andreapolimena on 13/02/16.
 */
public class TimePickerFragmentInizioMOD extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user

        StringBuilder temp = new StringBuilder();
        if(hourOfDay<10){
            temp.append("0"+hourOfDay);
        }else {
            temp.append(hourOfDay);
        }
        if(minute<10){
            temp.append(":0"+minute);
        }else {
            temp.append(":"+minute);
        }
        ((Button) getActivity().findViewById(R.id.oraInizio)).setText(temp);
    }
}