package com.example.mobilesngapp.JSON;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.example.mobilesngapp.Class.UserLogin;
import com.example.mobilesngapp.WindowLayoutInflater.DeviceAuthentication;
import com.example.mobilesngapp.WindowLayoutInflater.DeviceAuthenticationCodeConfirm;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSON_SendPhoneToAuthentication {

    Context con = null;
    public String IMEI;
    public String telephoneNumber;

    public JSON_SendPhoneToAuthentication(Context con, String IMEI, String _telephoneNumber) {
        this.con = con;
        this.IMEI = IMEI;
        telephoneNumber = _telephoneNumber;
        telephoneNumber=_telephoneNumber.substring(3);
        new JSON_SendPhoneToAuthentication.HttpAsyncTask2().execute("https://notif2.sng.com.pl/api/Mobile2AppSendCode");
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

            if(result.contains("true")){

                DeviceAuthentication deviceAuthentication=new DeviceAuthentication();
                deviceAuthentication.CloseWindow();
                DeviceAuthenticationCodeConfirm deviceAuthenticationCodeConfirm=new DeviceAuthenticationCodeConfirm();
                deviceAuthenticationCodeConfirm.show_DeviceAuthenticationCode(con);

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
