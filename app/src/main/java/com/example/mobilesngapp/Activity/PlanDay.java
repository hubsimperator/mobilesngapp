package com.example.mobilesngapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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



        ArrayList<String> workNameJobList = new ArrayList<String>();
        for (int i = 0; i < jobList.size(); i++){
            String job = jobList.get(i).WorkName;
            workNameJobList.add(job);
        }
        //Porównanie 2 tablic ze sobą. Jeżeli tablica status == zakonczona
        // to sprawdzic index statusow i porownac z tablica workname,
        // nastepnie dane miejse oznaczyc a finalnie zmienic kolor tla



        // Ustawienie adaptera
        ArrayAdapter<String> workNameJobAdapter =
                new ArrayAdapter<String>(this,
                        R.layout.activity_item_workplan,
                        R.id.workNameText,
                        workNameJobList
                );
        workListView.setAdapter(workNameJobAdapter);

        //Mozliwośc klikania list view

        workListView.setOnItemClickListener((AdapterView.OnItemClickListener) this);

    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.i("ListView", "You clicked Item: " + id + " at position:" + position);
        // Odpalenie nowego Activity przez Intenta
        Intent intent = new Intent();
        //intent.setClass(this, WorksMenu.class);
    }
}
