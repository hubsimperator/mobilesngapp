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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilesngapp.Class.DataBaseAdapter;
import com.example.mobilesngapp.Class.Job;
import com.example.mobilesngapp.JSON.JSON_GetJobList;
import com.example.mobilesngapp.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.mobilesngapp.Activity.Login.backButtonCount;

public class MainMenu extends AppCompatActivity {
    DatePickerDialog picker;
    EditText date;
    public static ArrayList<Job> jobList;
    public ArrayList<Job> customDateJobList;
    private GestureDetector gestureDetector;
    static String newDate = null;

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
                                newDate = year+ "-"+(monthOfYear+1)+"-"+dayOfMonth;
                                JSON_GetJobList json_getJobList=new JSON_GetJobList(MainMenu.this,"tdziura", newDate);
                                finish();
                            }
                        }, year, month, day);
           //
                //Bundle bundle = getIntent().getExtras();
                //customDateJobList = bundle.getParcelableArrayList("JobList");
                picker.show();
            }
        });


           // JobListFilter jobListFilter = new JobListFilter(jobList);
            //jobListListView(jobListFilter.filterByStatus(0));


        // Wyświetlenie danych z bazy danych
       //jobListListView(jobListFilter.filterByStatus(0));

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

    //ListView w Menu
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
    //onBackPressed - system wyłaczania aplikacji po podwójnym wciśnięciu przyciusku
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
            Toast.makeText(this, "Wciśniej jeszcze raz, aby zakończyć działanie aplikacji.", Toast.LENGTH_LONG).show();
            backButtonCount++;
        }
    }
}