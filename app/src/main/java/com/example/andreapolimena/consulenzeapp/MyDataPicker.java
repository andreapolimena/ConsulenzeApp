package com.example.andreapolimena.consulenzeapp;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by andreapolimena on 11/02/16.
 */
public class MyDataPicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    public static String monthsArray[]={"Gennaio", "Febbraio", "Marzo", "Aprile","Maggio", "Giugno", "Luglio", "Agosto", "Settembre","Ottobre", "Novembre", "Dicembre"};

    public MyDataPicker() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(getActivity(), String.valueOf(dayOfMonth)+" "+monthsArray[monthOfYear]+" "+String.valueOf(year),Toast.LENGTH_LONG ).show();
    }

    public void doDataPicker(View view){
        DialogFragment dialogFragment = new MyDataPicker();
        dialogFragment.show(getFragmentManager(), "Seleziona data");
    }
}
