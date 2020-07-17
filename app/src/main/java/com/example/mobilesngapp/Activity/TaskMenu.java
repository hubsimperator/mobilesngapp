package com.example.mobilesngapp.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilesngapp.R;

public class TaskMenu extends AppCompatActivity {

    Button planDniaButton;
    Button zakonczonePraceButton;
    Button aktualnePraceButton;
    View blueView;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskmenu);
        String passhash = "true";
        int status = 1;
        if (passhash == "true" && status != 0) {

            planDniaButton = findViewById(R.id.planDniaButton);
            aktualnePraceButton = findViewById(R.id.aktualnePraceButton);
            zakonczonePraceButton = findViewById(R.id.zakonczonePraceButton);
            blueView = findViewById(R.id.blueView);

            planDniaButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //context.startActivity();
                }
            });
            aktualnePraceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //context.startActivity();
                }
            });
            zakonczonePraceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //context.startActivity();
                }
            });

        }
    }


}
