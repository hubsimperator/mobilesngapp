package com.example.mobilesngapp.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilesngapp.Class.DataBaseAdapter;
import com.example.mobilesngapp.Class.Job;
import com.example.mobilesngapp.JSON.JSON_GetJobList;
import com.example.mobilesngapp.Other.JobListFilter;
import com.example.mobilesngapp.Other.SwipeMenu;
import com.example.mobilesngapp.R;

import java.text.DateFormat;
import java.util.ArrayList;

import static com.example.mobilesngapp.Activity.Login.backButtonCount;

public class PlannedWorksForDay extends AppCompatActivity {

    public static ArrayList<Job> jobList;
    EditText date;
    static String setNewCalendarDateForJSONtoGetNewContentForListView = null;
    DatePickerDialog datePickerFromCalendar;
    static Context context;
    static ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        context = PlannedWorksForDay.this;
         listView = (ListView) findViewById(R.id.planDayListView);


        try{
            Bundle extras = getIntent().getExtras();
            jobList  = extras.getParcelableArrayList("JobList");
        }catch (Exception e){
            Log.d("D", "Empty JobList.");
        }
try{
    Context m=context;
    JobListFilter jobListFilter = new JobListFilter(jobList);
    showJobListView(jobListFilter.filterByStatus(0));

}catch (Exception e){

}
        //WYŚWIETLENIE LISTVIEW

        //KALENDARZ
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

                datePickerFromCalendar = new DatePickerDialog(PlannedWorksForDay.this, new DatePickerDialog.OnDateSetListener() {
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
                        JSON_GetJobList json_getJobList = new JSON_GetJobList(PlannedWorksForDay.this,  "tdziura", setNewCalendarDateForJSONtoGetNewContentForListView);
                    }
                },year, month, day);
                datePickerFromCalendar.show();
            }
        });

        //SWIPOWANIE ACTIVITY
        SwipeMenu swipeMenu = new SwipeMenu(context);
        swipeMenu.gestureDetector();



    }

    //WYJŚCIE Z APLIKACJI POPRZEZ PDWÓJNE KLIKNIĘCIE NA PRZYCISK BACK
    @Override
    public void onBackPressed() {
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(context, "Wciśniej jeszcze raz, aby zakończyć działanie aplikacji.", Toast.LENGTH_LONG).show();
            backButtonCount++;
        }
    }
    //USTAIENIE ADAPTERA DO LISTVIEW
    public void showJobListView(ArrayList<Job> _jobList) {
        DataBaseAdapter adapter = new DataBaseAdapter(context, _jobList);
        listView.setAdapter(adapter);
    }
}
