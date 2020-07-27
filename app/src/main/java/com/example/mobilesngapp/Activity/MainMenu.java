package com.example.mobilesngapp.Activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilesngapp.Class.DataBaseAdapter;
import com.example.mobilesngapp.Class.Job;

import com.example.mobilesngapp.Other.JobListFilter;
import com.example.mobilesngapp.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainMenu extends AppCompatActivity {
    DatePickerDialog picker;
    EditText date;
    public static ArrayList<Job> jobList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Bundle extras = getIntent().getExtras();
        jobList  = extras.getParcelableArrayList("JobList");

        //ukrycie górnej belki
        getSupportActionBar().hide();

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
        JobListFilter jobListFilter = new JobListFilter(jobList);

        // Wyświetlenie danych z bazy danych
        jobListListView(jobListFilter.filterByStatus(0));

    }

    private void jobListListView(){
        DataBaseAdapter adapter = new DataBaseAdapter(this, jobList);
        ListView listView = (ListView) findViewById(R.id.planDayListView);
        listView.setAdapter(adapter);


    }
    private void jobListListView(ArrayList<Job> jobArrayList){
        DataBaseAdapter adapter = new DataBaseAdapter(this, jobArrayList);
        ListView listView = (ListView) findViewById(R.id.planDayListView);
        listView.setAdapter(adapter);


    }
}