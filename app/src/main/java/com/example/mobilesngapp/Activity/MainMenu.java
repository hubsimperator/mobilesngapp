package com.example.mobilesngapp.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilesngapp.Class.DataBaseAdapter;
import com.example.mobilesngapp.Class.Job;
import com.example.mobilesngapp.JSON.JSON_GetJobList;
import com.example.mobilesngapp.Other.JobListFilter;
import com.example.mobilesngapp.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainMenu extends AppCompatActivity {
    DatePickerDialog picker;
    EditText date;
    public static ArrayList<Job> jobList;
    private GestureDetector gestureDetector;
    static String calendarDateForJSON = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //wyciągnięcie danych z jsona
        try{
            Bundle extras = getIntent().getExtras();
            jobList  = extras.getParcelableArrayList("JobList");
        }catch (Exception e){
            Log.d("D", "Empty JobList.");
        }


        //GestureDetector
        gestureDetector = new GestureDetector(this, new SwipeGestureDetector());

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
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                final int month = calendar.get(Calendar.MONTH);
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
                                calendarDateForJSON = year+ "-"+(monthOfYear+1)+"-"+dayOfMonth;
                                JSON_GetJobList json_getJobList=new JSON_GetJobList(MainMenu.this,"tdziura", calendarDateForJSON);
                                //finish();
                            }
                        }, year, month, day);

           //
                //Bundle bundle = getIntent().getExtras();
                //customDateJobList = bundle.getParcelableArrayList("JobList");
                picker.show();
            }
        });

           //


        // WYŚWIETLENIE PRZEFILTROWANYCH DANYCH NA LISTVIEW ZA POMOCA METODY SHOWJOBLISTVIEW
        JobListFilter jobListFilter = new JobListFilter(jobList);
        showJobListView(jobListFilter.filterByStatus(0));

        //WYJŚCIE Z APLIKACJI POPRZEZ PODWÓJNE KLIKNĘCIE NA PRZYCISK BACK
        /*BackButton backButton = new BackButton();
        backButton.onBackPressed();*/

    }



    //Swipe Menu

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event)){
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void onLeft( ){
        Log.d("DEBUG_TAG", "onLeft");
        Intent intent = new Intent(MainMenu.this, CurrentWorks.class);
        startActivity(intent);
        finish();
    }

    //METODA USTAWIAJACA ADAPTER DO LISTVIEW
    public void showJobListView(ArrayList<Job> _jobList){
        DataBaseAdapter adapter = new DataBaseAdapter(MainMenu.this, _jobList);
        ListView listView = (ListView) findViewById(R.id.planDayListView);
        listView.setAdapter(adapter);
    }

    // GestureDetector
    private class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener{
        private static final int SWIPE_MIN_DISTANCE = 120;
        private static final int SWIPE_MAX_OFF_PATH = 200;
        private static final int SWIPE_THRESHOLD_VELOCITY = 200;


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            try{
                float diffAbs = Math.abs(e1.getY() - e2.getY());
                float diff = e1.getX() - e2.getX();

                if (diffAbs > SWIPE_MAX_OFF_PATH)
                    return false;
                //Przesunięcie palcem w lewo
                else if (diff > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY){
                    MainMenu.this.onLeft();
                }
            }catch (Exception e){
                Log.e("CurrentWorks Activity", "Error gesture detector. :c");
            }

            return false;
        }
    }
}