package com.example.mobilesngapp.Activity;

import android.app.TimePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.mobilesngapp.Class.Job;
import com.example.mobilesngapp.Other.TimePickerFragment;
import com.example.mobilesngapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EditWorkPlanActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    TextView workReqCode;
    TextView workStartTime;
    TextView workEndTime;
    TextView workName;
    TextView descriptionOfWorkTextView;
    TextView objectName;
    TextView location;
    TextView workKind;
    TextView dateStart;
    TextView dateEnd;
    TextView progressText;
    SeekBar progress;
    static ArrayList<Job> jobs;
    static int position;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editworkplan);

        getSupportActionBar().hide();

        workReqCode = findViewById(R.id.workReqCode);
        workStartTime = findViewById(R.id.workStartTime);
        workEndTime = findViewById(R.id.workEndTime);
        workName = findViewById(R.id.workName);
        descriptionOfWorkTextView = findViewById(R.id.descriptionOfWorkTextView);
        objectName = findViewById(R.id.objectName);
        location = findViewById(R.id.location);
        workKind = findViewById(R.id.workKind);
        dateStart = findViewById(R.id.dateStart);
        dateEnd = findViewById(R.id.dateEnd);
        progress = findViewById(R.id.progress);
        progressText = findViewById(R.id.progressText);

        String startJob = jobs.get(position).WorkStart;
        String endJob = jobs.get(position).WorkEnd;
        Date WorkStart = null;
        try {
            WorkStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startJob);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        String timeStartJob = new SimpleDateFormat("HH:mm").format(WorkStart);
        String startJobDate = new SimpleDateFormat("yyyy-MM-dd").format(WorkStart);

        Date WorkEnd = null;
        try {
            WorkEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endJob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String timeEndJob = new SimpleDateFormat("HH:mm").format(WorkEnd);
        String endJobDate = new SimpleDateFormat("yyyy-MM-dd").format(WorkEnd);

        workReqCode.setText(jobs.get(position).WorkReqCode);
        workName.setText(jobs.get(position).WorkReqName);
        descriptionOfWorkTextView.setText(jobs.get(position).WorkDescription);
        objectName.setText(jobs.get(position).ObjectName);
        location.setText(jobs.get(position).Location);
        workKind.setText(jobs.get(position).WorkKind);
        dateStart.setText(startJobDate);
        workStartTime.setText(timeStartJob);
        dateEnd.setText(endJobDate);
        workEndTime.setText(timeEndJob);
        progressText.setText("0");

        progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                //int val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                progressText.setText("" + progress);
                //progressText.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        workStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                workStartTime.setTypeface(Typeface.DEFAULT_BOLD);
            }
        });


        workEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                workEndTime.setTypeface(Typeface.DEFAULT_BOLD);
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (workStartTime.getTypeface().equals(Typeface.DEFAULT_BOLD)){
            String editedMin = String.valueOf(hourOfDay);
            if (hourOfDay <= 9){
                editedMin = "0" + hourOfDay;
            }else{
                workStartTime.setText(hourOfDay + ":" + minute);
            }
            if (minute <= 9){
                workStartTime.setText(editedMin + ":0" + minute);
            }else{
                workStartTime.setText(editedMin + ":" + minute);
            }
            workStartTime.setTypeface(Typeface.DEFAULT);
        }else if (workEndTime.getTypeface().equals(Typeface.DEFAULT_BOLD)){
            String editedMin = String.valueOf(hourOfDay);
            if (hourOfDay <= 9){
                editedMin = "0" + hourOfDay;
            }else{
                workEndTime.setText(hourOfDay + ":" + minute);
            }
            if (minute <= 9){
                workEndTime.setText(editedMin + ":0" + minute);
            }else{
                workEndTime.setText(editedMin + ":" + minute);
            }
            workEndTime.setTypeface(Typeface.DEFAULT);
        }
    }

    public ArrayList<Job> getJobsData(ArrayList<Job> result, int position){
        jobs = result;
        this.position = position;
        return jobs;
    }
}
