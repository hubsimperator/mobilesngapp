package com.example.mobilesngapp.Activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilesngapp.Class.Job;
import com.example.mobilesngapp.R;

import java.util.ArrayList;

public class PlanDay extends AppCompatActivity {

    private ArrayAdapter<String> adapter ;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        textView = findViewById(R.id.workNameText);

        //Ustawienie listy na cały ekran
        ListView workListView = new ListView(this);
        setContentView(workListView);

        //pobranie danych z jsona
        Bundle extras = getIntent().getExtras();
        ArrayList<Job> jobList  = extras.getParcelableArrayList("JobList");

        //utworzenie loklanej tablicy i wprowadzenie danych z jsona do tabicy
        ArrayList<String> workNameJobList = new ArrayList<String>();
        for (int i = 0; i < jobList.size(); i++){
            String job = jobList.get(i).WorkName;
            /*if (jobList.get(i).StatusId.equals(501)){
                textView.setTextColor(Color.GRAY);
                workNameJobList.add(job);
            } else if (jobList.get(i).StatusId.equals(520)){
                textView.setTextColor(Color.GREEN);
                workNameJobList.add(job);
            } else{
                workNameJobList.add(job);
            }*/
            workNameJobList.add(job);
        }

        // Ustawienie adaptera
        ArrayAdapter<String> workNameJobAdapter =
                new ArrayAdapter<String>(this,
                        R.layout.activity_item_workplan,
                        R.id.workNameText,
                        workNameJobList
                );
        workListView.setAdapter(workNameJobAdapter);

    }
}
