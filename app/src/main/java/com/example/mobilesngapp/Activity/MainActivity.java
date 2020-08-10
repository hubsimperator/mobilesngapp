package com.example.mobilesngapp.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mobilesngapp.Class.Job;
import com.example.mobilesngapp.JSON.JSON_GetJobList;
import com.example.mobilesngapp.Other.SquadAlertDialog;
import com.example.mobilesngapp.Other.ViewPagerAdapter;
import com.example.mobilesngapp.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static ViewPager2 viewPager2;

    TextView planDay;
    TextView currentWorks;
    TextView completedWorks;
    EditText calendar;

    DatePickerDialog datePicker;

    static Context context;

    public static ArrayList<Job> jobs;
    static String setNewCalendarDateForJSONtoGetNewContentForListView = null;
    static List<String> numberOfPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        getSupportActionBar().hide();

        calendar = findViewById(R.id.calendarEditText);
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

        /* Wywołanie adaptera viewPager tylko raz w momencie uruchomienia onCreate */

        viewPager2.setAdapter(new ViewPagerAdapter(context, numberOfPages, viewPager2,jobs, -1));

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
                        SquadAlertDialog squadAlertDialog = new SquadAlertDialog();
                        /** Uruchomienie Jsona z nową wybraną datą przez użytkownika
                         *  Wprowadzenie wybranej daty do AlertDialog*/
                        setNewCalendarDateForJSONtoGetNewContentForListView = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        squadAlertDialog.getDateFromCalendar(setNewCalendarDateForJSONtoGetNewContentForListView);
                        JSON_GetJobList json_getJobList = new JSON_GetJobList(context,  "rwardal", setNewCalendarDateForJSONtoGetNewContentForListView);
                    }
                },year,month,day);
                datePicker.show();
            }
        });
//---------------------Pogrubienie Menu nawigacyjnego-----------------------------//

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0){
                    planDay.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    currentWorks.setTypeface(Typeface.DEFAULT);
                    completedWorks.setTypeface(Typeface.DEFAULT);
                }else if(position == 1){
                    currentWorks.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    planDay.setTypeface(Typeface.DEFAULT);
                    completedWorks.setTypeface(Typeface.DEFAULT);
                }else if (position == 2){
                    completedWorks.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    currentWorks.setTypeface(Typeface.DEFAULT);
                    planDay.setTypeface(Typeface.DEFAULT);
                }
            }
        });
//---------------------------------------------------------------------------------//

        //Możliwość zmiany kart poprzez wciśnięcie texty "PLAN DNIA" / "AKTUALNE PRACE" / "ZAKOŃCZONE PRACE"
        planDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager2.setCurrentItem(0);
            }
        });
        currentWorks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager2.setCurrentItem(1);
            }
        });
        completedWorks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager2.setCurrentItem(2);
            }
        });
    }

     //Po za onCreate
    /** Metoda ta przy pierwsyzm wykonaniu i utworzeniu onCreate MainActivity ma numberOfPages i viewPager równe NULL.
     * Zwraca wtedy jobs = (ArrayList<Job> jobs)
     * Następnie uruchamiany jest kod z onCreate "viewPager2.setAdapter(new ViewPagerAdapter(context, numberOfPages, viewPager2,jobs));"
     * W tym momencie następuje przetworzenie danych i wyświetlenie ich na ekran z obecną datą.
     * Z racji tego że pola numberOfPages, context, viewPager2 są statyczne w momencie zmiany daty pamięta wartość tych pul i wchodzi do elsa.
     * jobs jest nadpisywane a adapter jest wykonany z nowymi danymi pobranymi z JSON_GetJobList.
     * */
    public ArrayList<Job> getDataFromJson(ArrayList<Job> result){
        jobs = result;
        if (numberOfPages == null || viewPager2 == null){
            return jobs;
        }else{
            viewPager2.setAdapter(new ViewPagerAdapter(context, numberOfPages, viewPager2,jobs, -1));
        }
        return null;
    }
}
