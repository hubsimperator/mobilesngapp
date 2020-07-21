package com.example.mobilesngapp.WindowLayoutInflater;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.mobilesngapp.R;

public class DeviceAuthentication {
    AlertDialog alertDialog;
    View view;

    public void show_DeviceAuthentication(Context con){


        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(con)
                .setNeutralButton("Powrót",null);

        LayoutInflater inflater = (LayoutInflater)   con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.authorization_phonenumber,null);

        final EditText edt = (EditText) view.findViewById(R.id.phonenumber_et);

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

    }


