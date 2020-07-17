package com.example.mobilesngapp.Activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilesngapp.R;

import java.text.DateFormat;
import java.util.Calendar;

public class MainMenu extends AppCompatActivity {
    DatePickerDialog picker;
    EditText date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        date =(EditText) findViewById(R.id.dateText);
        date.setInputType(InputType.TYPE_NULL);
        Calendar c = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());
        date.setText(currentDate);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                picker = new DatePickerDialog(MainMenu.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String newDay = String.valueOf(dayOfMonth);
                                if (dayOfMonth <= 9){
                                   newDay = "0" + dayOfMonth;
                                }else{
                                    date.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
                                }
                                if(monthOfYear < 9){
                                    date.setText(newDay + ".0" + (monthOfYear + 1) + "." + year);
                                }else{
                                    date.setText(newDay + "." + (monthOfYear + 1) + "." + year);
                                }


                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }
}