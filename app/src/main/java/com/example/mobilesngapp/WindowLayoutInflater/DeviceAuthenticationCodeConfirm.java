package com.example.mobilesngapp.WindowLayoutInflater;

import android.app.AlertDialog;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mobilesngapp.JSON.JSON_SendCodeToConfirmAuthentication;
import com.example.mobilesngapp.JSON.JSON_SendPhoneToAuthentication;
import com.example.mobilesngapp.R;

public class DeviceAuthenticationCodeConfirm {
   public  AlertDialog alertDialog;
    View view;

    public void show_DeviceAuthenticationCode(final Context con){

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(con)
                .setNeutralButton("Powrót",null);

        LayoutInflater inflater = (LayoutInflater)   con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.authorization_confirmcode,null);

        final EditText edt = (EditText) view.findViewById(R.id.codeAth_et);


        Button sendPhonetoAuth =(Button) view.findViewById(R.id.sendCodeAuth_bt);
        sendPhonetoAuth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                TelephonyManager telephonyManager = (TelephonyManager) con.getSystemService(Context.TELEPHONY_SERVICE);
                String IMEI ="";
                try {
                    IMEI = telephonyManager.getDeviceId();
                }catch (NullPointerException ne){

                }


                String codeAuth=edt.getText().toString();
                JSON_SendCodeToConfirmAuthentication json_sendCodeToConfirmAuthentication=new JSON_SendCodeToConfirmAuthentication(con,IMEI,codeAuth);
               // JSON_SendPhoneToAuthentication json_sendPhoneToAuthentication=new JSON_SendPhoneToAuthentication(con,IMEI,phoneNumber);
            }
        });



        dialogBuilder.setView(view);
        alertDialog =dialogBuilder.create();
        alertDialog.show();
    };

    public void CloseWindow(){
        alertDialog.show();

    }

    }


