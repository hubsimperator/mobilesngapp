package com.example.mobilesngapp.Other;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import com.example.mobilesngapp.Class.SMS;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmsReader {

    public static  long CompareDate(Long startDate,Long endDate){

        return((endDate-startDate)/1000/60);

    }

    public void ReadSms(Context con){

        // public static final String INBOX = "content://sms/inbox";
// public static final String SENT = "content://sms/sent";
// public static final String DRAFT = "content://sms/draft";
        Cursor cursor = con.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        ArrayList<SMS> listaSms=new ArrayList<>();

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "";
                String body=null;
                String sender=null;
                String recipient=null;
                String data=null;
                for(int idx=0;idx<cursor.getColumnCount();idx++)
                {
                    //
                    // msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
                    if(cursor.getColumnName(idx).equals("body")){
                        body=cursor.getString(idx);
                    }

                    if(cursor.getColumnName(idx).equals("date")){
                        data=cursor.getString(idx);
                    }

                    if(cursor.getColumnName(idx).equals("address") && cursor.getString(idx).equals("SNG")){

                        sender=cursor.getString(idx);
                    }

                    if(body !=null && data != null && sender != null){
                        long i =  new Date().getTime();
                        long diff= i- Long.valueOf(data);

                        if(CompareDate(Long.valueOf(data),i)<5){
                            Log.d("tag","NOWA WIADOMOsc!");
                            Log.d("tag","Treść : "+body);
                            Log.d("tag","Od : "+sender);
                            String kod=body.substring(body.length()-6,body.length());
                            Log.d("tag","Kod " + kod);
                        }

                        SMS sms=new SMS(body,sender,"ktoś");
                        listaSms.add(sms);
                        break;

                    }
                }

                Log.d("a","a");
                // use msgData
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }
    }

}


