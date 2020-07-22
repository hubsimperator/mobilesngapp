package com.example.mobilesngapp.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilesngapp.JSON.JSON_GetJobList;
import com.example.mobilesngapp.R;

import java.text.DateFormat;
import java.util.Calendar;

public class MainMenu extends AppCompatActivity {
    DatePickerDialog picker;
    EditText date;
    Button planDay;
    Button works;
    Button endWorks;
    Button squad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Kalendarz
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
        // Menu
        planDay = findViewById(R.id.planDniaButton);
        works = findViewById(R.id.aktualnePraceButton);
        endWorks = findViewById(R.id.zakonczonePraceButton);
        squad = findViewById(R.id.brygadaButton);

        //Przejście na nowy ekran po wciśnięciu buttona PLAN DNIA
        planDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(view.getContext(), PlanDay.class);
                //startActivityForResult(intent, 0);
                JSON_GetJobList json_getJobList=new JSON_GetJobList(MainMenu.this,"admin");
            }
        });
        // Nowy ekran ZAPLANOWANE PRACE
        works.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CurrentWorks.class);
                startActivityForResult(intent, 0);
            }
        });
        // Nowy ekran ZAKOŃCZONE PRACE
        endWorks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EndWorks.class);
                startActivityForResult(intent, 0);
            }
        });
        // Nowy ekran BRYGADA
        squad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Squad.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}