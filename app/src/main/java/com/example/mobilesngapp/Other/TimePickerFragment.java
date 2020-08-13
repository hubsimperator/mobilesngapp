package com.example.mobilesngapp.Other;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment {


    @Override
    public void onDismiss(DialogInterface dialog) {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // do wybrania wygląd zagara

        int scrollableTimer = 3;
        int clockTimer = 1;
        return new TimePickerDialog(
                getActivity(),
                scrollableTimer,
                (TimePickerDialog.OnTimeSetListener) getActivity(),
                hour,
                minute,
                DateFormat.is24HourFormat(getActivity()
                ));
    }
}
