package com.example.mobilesngapp.Other;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class Permission {

    public boolean checkPermissions(Context con) {

        if (ActivityCompat.checkSelfPermission(con,
                Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        if (ActivityCompat.checkSelfPermission(con,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        if (ActivityCompat.checkSelfPermission(con,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        if (ActivityCompat.checkSelfPermission(con,
                Manifest.permission.VIBRATE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }if (ActivityCompat.checkSelfPermission(con,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        if (ActivityCompat.checkSelfPermission(con,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        if (ActivityCompat.checkSelfPermission(con,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        if (ActivityCompat.checkSelfPermission(con,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        if (ActivityCompat.checkSelfPermission(con,
                Manifest.permission.SET_ALARM) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        if (ActivityCompat.checkSelfPermission(con,
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        if (ActivityCompat.checkSelfPermission(con,
                Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        if (ActivityCompat.checkSelfPermission(con,
                Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        return true;
    }

    public void setPermissions(Activity activity) {
        ActivityCompat.requestPermissions((Activity) activity, new String[]{
                Manifest.permission.INTERNET , Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.VIBRATE ,Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.SET_ALARM,Manifest.permission.READ_PHONE_STATE,Manifest.permission.RECEIVE_SMS,Manifest.permission.READ_SMS}, 1);
    }

}



