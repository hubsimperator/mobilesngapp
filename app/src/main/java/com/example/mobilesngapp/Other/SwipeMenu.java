package com.example.mobilesngapp.Other;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.example.mobilesngapp.Activity.CurrentWorks;

public class SwipeMenu extends Activity {
    public GestureDetector gestureDetector;
    public Context context;


    public SwipeMenu(Context context) {
        this.context = context;
    }

    public void gestureDetector(){
        gestureDetector = new GestureDetector(context, new SwipeMenu.SwipeGestureDetector());
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event)){
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void onLeft( ){
        Log.d("DEBUG_TAG", "onLeft");
        Intent intent = new Intent(context, CurrentWorks.class);
        startActivity(intent);
        finish();
    }

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
                    SwipeMenu.this.onLeft();
                }
            }catch (Exception e){
                Log.e("CurrentWorks Activity", "Error gesture detector. :c");
            }

            return false;
        }
    }
}
