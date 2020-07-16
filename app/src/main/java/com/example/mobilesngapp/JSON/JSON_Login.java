package com.example.mobilesngapp.JSON;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.TextView;


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

public class JSON_Login {

    Context con = null;
    String User = "", Pass = "",IMEI="",telephoneNumber="", Token = "", AppVer = "";
    TextView er = null;
    ProgressDialog progressDialog;

    public void StartUpdate(String Login, String Password,String _IMEI,String _telephoneNumber, Context context, ProgressDialog _pd, TextView _er) {
        con = context;
        User = Login;
        Pass = Password;
        progressDialog = _pd;
        telephoneNumber=_telephoneNumber;
        IMEI=_IMEI;
        er = _er;

        new HttpAsyncTask2().execute("https://notif2.sng.com.pl/api/GetUserMobileApp");
    }
    public String POST(String url) {
        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            String json = "";
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("User",User);
            jsonObject.accumulate("Password",Pass);
            jsonObject.accumulate("IMEI",IMEI);
            jsonObject.accumulate("Tel",telephoneNumber);
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
        }
        return result;
    }
    private class HttpAsyncTask2 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return POST(urls[0]);
        }


    @Override
    protected void onPostExecute(String result) {
            if(result.contains("true"))
            {
               // con.startActivity(new Intent(con, SplashScreen.class));

            }
            else
            {
               // er.setTextColor(0xFFCC0000);
              //  er.setGravity(Gravity.CENTER);
              //  er.setText(result);
                progressDialog.hide();
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
