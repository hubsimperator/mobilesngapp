package com.example.mobilesngapp.Activity;

import android.app.Activity;
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

import com.example.mobilesngapp.Class.DataBaseAdapter;
import com.example.mobilesngapp.Class.Job;
import com.example.mobilesngapp.Other.JobListFilter;
import com.example.mobilesngapp.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CompletedWork extends Activity {
    DatePickerDialog picker;
    EditText date;
    public static ArrayList<Job> jobList;
    private GestureDetector gestureDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completedwork);

        jobList = new ArrayList<>();

        //gesturedetector
        gestureDetector = new GestureDetector(this, new SwipeGestureDetector());

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
                picker = new DatePickerDialog(CompletedWork.this,
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

        jobListListView(jobListFilter.filterByStatus(2));
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event)){
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void onRight(){
        Log.d("DEBUG_TAG", "onRight");
        Intent intent = new Intent(CompletedWork.this, CurrentWorks.class);
        startActivity(intent);
        finish();
    }

    private void jobListListView(ArrayList<Job> jobArrayList) {
        DataBaseAdapter adapter = new DataBaseAdapter(this, jobArrayList);
        ListView listView = (ListView) findViewById(R.id.completedWorksListView);
        listView.setAdapter(adapter);
    }

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
                //Przesunece palcem w prawo
                if (-diff > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY){
                    CompletedWork.this.onRight();
                }
            }catch (Exception e){
                Log.e("CurrentWorks Activity", "Error gesture detector. :c");
            }

            return false;
        }
    }
}
