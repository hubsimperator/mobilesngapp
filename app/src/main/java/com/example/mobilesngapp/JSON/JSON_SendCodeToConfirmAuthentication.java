package com.example.mobilesngapp.JSON;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.mobilesngapp.Activity.MainMenu;
import com.example.mobilesngapp.WindowLayoutInflater.DeviceAuthentication;
import com.example.mobilesngapp.WindowLayoutInflater.DeviceAuthenticationCodeConfirm;
import com.example.mobilesngapp.WindowLayoutInflater.DeviceAuthenticationStatus;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSON_SendCodeToConfirmAuthentication {

    Context con = null;
    public String IMEI;
    public String smsCode;

    public JSON_SendCodeToConfirmAuthentication(Context con, String IMEI, String smsCode) {
        this.con = con;
        this.IMEI = IMEI;
        this.smsCode = smsCode;
        new JSON_SendCodeToConfirmAuthentication.HttpAsyncTask2().execute("https://notif2.sng.com.pl/api/Mobile2AppCheckCode");

    }

    private String POST(String url) {
        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            String json = "";
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("IMEI",IMEI);
            jsonObject.accumulate("SMSCode",smsCode);
            json = jsonObject.toString();
            StringEntity se = new StringEntity(json);
            httpPost.setEntity(se);
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Accept", "application/json");
            HttpResponse httpResponse = httpclient.execute(httpPost);
            inputStream = httpResponse.getEntity().getContent();
            if (inputStream != null) result = convertInputStreamToString(inputStream);
            else result = "Nie działa";
        } catch (Exception e) {
            result=null;
        }
        return result;
    }
    private class HttpAsyncTask2 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String post= POST(urls[0]);
            return post;
        }


        @Override
        protected void onPostExecute(String result) {
            Log.d("aa","a");
            if(result.contains("true")){

                DeviceAuthenticationCodeConfirm deviceAuthenticationCodeConfirm=new DeviceAuthenticationCodeConfirm();
                deviceAuthenticationCodeConfirm.CloseWindow();

                DeviceAuthenticationStatus deviceAuthenticationStatus=new DeviceAuthenticationStatus();
                deviceAuthenticationStatus.ShowDeviceAuthenticationStatus(con,true);

            }
        }
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null) result += line;
        inputStream.close();
        return result;
    }
}
