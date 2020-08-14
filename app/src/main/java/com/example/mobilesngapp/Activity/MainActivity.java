package com.example.mobilesngapp.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mobilesngapp.Class.Job;
import com.example.mobilesngapp.JSON.JSON_GetJobList;
import com.example.mobilesngapp.Other.DatePickerFragment;
import com.example.mobilesngapp.Other.SquadAlertDialog;
import com.example.mobilesngapp.Other.ViewPagerAdapter;
import com.example.mobilesngapp.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    static ViewPager2 viewPager2;

    TextView planDay;
    TextView currentWorks;
    TextView completedWorks;
    TextView calendar;
    Spinner brigadeSpinner;

    static Context context;

    public static ArrayList<Job> jobs;
    static String setNewCalendarDateForJSONtoGetNewContentForListView = null;
    static List<String> numberOfPages;
    public static ArrayList<String> brigades = new ArrayList<String>();
    public static HashSet<String> brigades2 = new HashSet<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        getSupportActionBar().hide();

        calendar = findViewById(R.id.calendarTextView);
        viewPager2 = findViewById(R.id.viewPager2);
        planDay = findViewById(R.id.planDayTextView);
        currentWorks = findViewById(R.id.currentWorksTextView);
        completedWorks = findViewById(R.id.completedWorksTextView);
        brigadeSpinner = findViewById(R.id.spinner);

        numberOfPages = new ArrayList<>();
        numberOfPages.add("First Screen");
        numberOfPages.add("Second Screen");
        numberOfPages.add("Third Screen");

        /* Wywołanie adaptera viewPager tylko raz w momencie uruchomienia onCreate */

        viewPager2.setAdapter(new ViewPagerAdapter(context, numberOfPages, viewPager2,jobs, -1));

        //Kalendarz i jego wywołanie
        calendar.setInputType(InputType.TYPE_NULL);
        Calendar c = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());
        calendar.setText(currentDate);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

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


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, brigades);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brigadeSpinner.setAdapter(adapter);
        brigadeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Możliwość zmiany kart poprzez wciśnięcie textu "PLAN DNIA" / "AKTUALNE PRACE" / "ZAKOŃCZONE PRACE"
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
     * Z racji tego że pola numberOfPages, context, viewPager2 są statyczne w momencie zmiany daty pamięta wartość tych pól i wchodzi do elsa.
     * jobs jest nadpisywane a adapter jest wykonany z nowymi danymi pobranymi z JSON_GetJobList.
     * */
    public ArrayList<Job> getDataFromJson(ArrayList<Job> result){
        jobs = result;
        if (numberOfPages == null || viewPager2 == null){
            for (int i = 0; i < result.size(); i++){
                brigades.add(result.get(i).ResourceName);
            }
            //usunięcie powtarzających się brygad
            brigades2.addAll(brigades);
            brigades.clear();
            brigades.addAll(brigades2);
            return jobs;
        }else{
            viewPager2.setAdapter(new ViewPagerAdapter(context, numberOfPages, viewPager2,jobs, -1));
            for (int i = 0; i < result.size(); i++){
                brigades.add(result.get(i).ResourceName);
            }
            //usunięcie powtarzających się brygad
            brigades2.addAll(brigades);
            brigades.clear();
            brigades.addAll(brigades2);
        }
        return null;
    }

    //metoda do wyboru daty, szybsza niż w wersji 1
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
        SquadAlertDialog squadAlertDialog = new SquadAlertDialog();
        setNewCalendarDateForJSONtoGetNewContentForListView = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
        squadAlertDialog.getDateFromCalendar(setNewCalendarDateForJSONtoGetNewContentForListView);
        JSON_GetJobList json_getJobList = new JSON_GetJobList(context,  "rwardal", setNewCalendarDateForJSONtoGetNewContentForListView);
    }
}
