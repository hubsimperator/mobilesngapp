package com.example.mobilesngapp.Other;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

public class InfoAboutPhone {

    public String getIMEI(Context con){

        TelephonyManager telephonyManager = (TelephonyManager) con.getSystemService(Context.TELEPHONY_SERVICE);
        String IMEI ="";
        try {
            IMEI = telephonyManager.getDeviceId();
        }catch (NullPointerException ne){


            Log.d("a","aa");
        }

        return IMEI;
    }

}
