package com.example.mobilesngapp.WindowLayoutInflater;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.mobilesngapp.R;

public class DeviceAuthenticationStatus {
   public static AlertDialog alertDialog;
    View view;

    public void ShowDeviceAuthenticationStatus(final Context con,Boolean status){

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(con)
                .setNeutralButton("Zamknij",null)
                .setCancelable(false);

        LayoutInflater inflater = (LayoutInflater)   con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(status) {
            view = inflater.inflate(R.layout.authorization_statusconfirmed, null);
        }else{
            view = inflater.inflate(R.layout.authorization_statusfailed, null);
        }

        dialogBuilder.setView(view);
        alertDialog =dialogBuilder.create();
        alertDialog.show();
    };


    public void CloseWindow(){
        alertDialog.dismiss();

    }

    }


