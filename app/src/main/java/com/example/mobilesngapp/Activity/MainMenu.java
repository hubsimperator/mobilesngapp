package com.example.mobilesngapp.Activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilesngapp.R;

public class MainMenu extends AppCompatActivity {

    LinearLayout linearLayout;
    TextView dayPlanTextView;
    TextView text2;
    TextView text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskmenu);

        linearLayout = findViewById(R.id.test1);
        dayPlanTextView = findViewById(R.id.dayPlanTextView);
        text2 = findViewById(R.id.textView6);
        text3 = findViewById(R.id.textView7);
    }
}
