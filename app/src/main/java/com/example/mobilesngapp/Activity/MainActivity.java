package com.example.mobilesngapp.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mobilesngapp.Class.Job;
import com.example.mobilesngapp.JSON.JSON_GetJobList;
import com.example.mobilesngapp.Other.ArrayListAdapter;
import com.example.mobilesngapp.Other.JobListFilter;
import com.example.mobilesngapp.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    ListView jobListView;

    TextView planDay;
    TextView currentWorks;
    TextView completedWorks;
    EditText calendar;

    DatePickerDialog datePicker;

    Context context = this;

    public static ArrayList<Job> jobList;
    static String setNewCalendarDateForJSONtoGetNewContentForListView = null;
    List<String> numberOfPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewPager2);
        planDay = findViewById(R.id.planDayTextView);
        currentWorks = findViewById(R.id.currentWorksTextView);
        completedWorks = findViewById(R.id.completedWorksTextView);

//---------------------Number of Pages for viewAdapter2---------------------//
        numberOfPages = new ArrayList<>();
        numberOfPages.add("First Screen");
        numberOfPages.add("Second Screen");
        numberOfPages.add("Third Screen");
//--------------------------------------------------------------------------//

/*
Ustawienie adaptera do viewPager2.


 */
        viewPager2.setAdapter();

//---------------------CALENDAR---------------------//
        calendar.setInputType(InputType.TYPE_NULL);

        Calendar c = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());

        calendar.setText(currentDate);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                final int month = c.get(Calendar.MONTH);
                final int day = c.get(Calendar.DAY_OF_MONTH);

                datePicker = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        String editedDay = String.valueOf(dayOfMonth);

                        if (dayOfMonth <= 9){
                            editedDay = "0" + dayOfMonth;
                        }else{
                            calendar.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
                        }
                        if (monthOfYear < 9){
                            calendar.setText(editedDay + ".0" + (monthOfYear + 1) + "." + year);
                        }else{
                            calendar.setText(editedDay + "." + (monthOfYear + 1) + "." + year);
                        }
                        //json
                        setNewCalendarDateForJSONtoGetNewContentForListView = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        JSON_GetJobList json_getJobList = new JSON_GetJobList(context,  "tdziura", setNewCalendarDateForJSONtoGetNewContentForListView);
                    }
                },year,month,day);
                datePicker.show();
            }
        });
//--------------------------------------------------//

    }

//---------------------Po za onCreate---------------------//
    public void showJobListView(ArrayList<Job> _jobList){
        JobListFilter jobListFilter = new JobListFilter(_jobList);
        ArrayListAdapter adapter = new ArrayListAdapter(context, jobListFilter.filterByStatus(0));
        jobListView.setAdapter(adapter);
    }

}
