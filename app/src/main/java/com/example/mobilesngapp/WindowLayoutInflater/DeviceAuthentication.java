package com.example.mobilesngapp.WindowLayoutInflater;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mobilesngapp.JSON.JSON_SendPhoneToAuthentication;
import com.example.mobilesngapp.R;

public class DeviceAuthentication {
   public static AlertDialog alertDialog;
    View view;

    public void show_DeviceAuthentication(final Context con){


        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(con)
                .setNeutralButton("Powrót",null)
                .setCancelable(false);


        LayoutInflater inflater = (LayoutInflater)   con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.authorization_phonenumber,null);

        final EditText edt = (EditText) view.findViewById(R.id.phonenumber_et);


        Button sendPhonetoAuth =(Button) view.findViewById(R.id.sendPhoneNumberAuth_bt);
        sendPhonetoAuth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                TelephonyManager telephonyManager = (TelephonyManager) con.getSystemService(Context.TELEPHONY_SERVICE);
                String IMEI ="";
                try {
                    IMEI = telephonyManager.getDeviceId();
                }catch (NullPointerException ne){

                }
                if(IMEI==null){
                    IMEI = Settings.Secure.getString(
                            con.getContentResolver(),
                            Settings.Secure.ANDROID_ID);
                }

                String phoneNumber=edt.getText().toString();
                JSON_SendPhoneToAuthentication json_sendPhoneToAuthentication=new JSON_SendPhoneToAuthentication(con,IMEI,phoneNumber);
            }
        });



        edt.setText("+48");
        Selection.setSelection(edt.getText(), edt.getText().length());
        edt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().startsWith("+48")){
                    edt.setText("+48");
                    Selection.setSelection(edt.getText(), edt.getText().length());

                }

            }
        });


        dialogBuilder.setView(view);
        alertDialog =dialogBuilder.create();
        alertDialog.show();
    };


    public void CloseWindow(){
        alertDialog.dismiss();

    }

    }


