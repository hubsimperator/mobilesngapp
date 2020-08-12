package com.example.mobilesngapp.Activity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.mobilesngapp.Other.TimePickerFragment;
import com.example.mobilesngapp.R;

public class EditWorkPlanActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    TextView workReqCode;
    TextView workStartTime;
    TextView workEndTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editworkplan);

        getSupportActionBar().hide();

        workReqCode = findViewById(R.id.workReqCode);
        workStartTime = findViewById(R.id.workStartTime);
        workEndTime = findViewById(R.id.workEndTime);


        workStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        workStartTime.setText(hourOfDay + ":" + minute);
    }
}
