package com.example.mobilesngapp.Other;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.mobilesngapp.JSON.JSON_GetJobList;
import com.example.mobilesngapp.R;

import java.text.DateFormat;

public class Calendar extends Activity {
    EditText date;
    static String setNewCalendarDateForJSONtoGetNewContentForListView = null;
    DatePickerDialog datePickerFromCalendar;

    public void calendar(){
        date = (EditText) findViewById(R.id.dateText);
        date.setInputType(InputType.TYPE_NULL);
        java.util.Calendar c = java.util.Calendar.getInstance();

        String currentDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());
        date.setText(currentDate);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final java.util.Calendar calendar = java.util.Calendar.getInstance();
                int year = calendar.get(java.util.Calendar.YEAR);
                final int month = calendar.get(java.util.Calendar.MONTH);
                final int day = calendar.get(java.util.Calendar.DAY_OF_MONTH);

                datePickerFromCalendar = new DatePickerDialog(Calendar.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        String newDay = String.valueOf(dayOfMonth);

                        if (dayOfMonth <= 9){
                            newDay = "0" + dayOfMonth;
                        }else{
                            date.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
                        }
                        if (monthOfYear < 9){
                            date.setText(newDay + ".0" + (monthOfYear + 1) + "." + year);
                        }else{
                            date.setText(newDay + "." + (monthOfYear + 1) + "." + year);
                        }

                        setNewCalendarDateForJSONtoGetNewContentForListView = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        JSON_GetJobList json_getJobList = new JSON_GetJobList(Calendar.this,  "tdziura", setNewCalendarDateForJSONtoGetNewContentForListView);
                    }
                },year, month, day);
                datePickerFromCalendar.show();
            }
        });
    }
}
