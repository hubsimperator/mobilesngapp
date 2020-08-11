package com.example.mobilesngapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilesngapp.R;

public class SplashActivity extends AppCompatActivity {

    /*Animation topAnimation;
    ImageView logo;*/

    ProgressBar loadingCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        loadingCircle = findViewById(R.id.loading_circle);

        getSupportActionBar().hide();

        //logo.findViewById(R.id.logo_splashscreen);

        //Animation
        /*topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        logo.setAnimation(topAnimation);*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }
}
