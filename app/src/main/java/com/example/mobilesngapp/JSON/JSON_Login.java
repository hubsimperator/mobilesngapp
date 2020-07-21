package com.example.mobilesngapp.JSON;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.TextView;

import com.example.mobilesngapp.Class.UserLogin;

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

public class JSON_Login {

    Context con = null;
    public String User = "", Pass = "",IMEI="",telephoneNumber="";
    public TextView er = null;
    public ProgressDialog progressDialog;

    public JSON_Login(Context con, String user, String pass, String IMEI, String telephoneNumber, TextView er, ProgressDialog progressDialog) {
        this.con = con;
        User = user;
        Pass = pass;
        this.IMEI = IMEI;
        this.telephoneNumber = telephoneNumber;
        this.er = er;
        this.progressDialog = progressDialog;
        new HttpAsyncTask2().execute("https://notif2.sng.com.pl/api/GetUserMobile2App");

    }

    private String POST(String url) {
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
            result=null;
        }
        return result;
    }
    private class HttpAsyncTask2 extends AsyncTask<String, Void, UserLogin> {
        @Override
        protected UserLogin doInBackground(String... urls) {
            String post= POST(urls[0]);
            return deserialize_json(post);
        }


    @Override
    protected void onPostExecute(UserLogin result) {
        if(result.Status==1){
            progressDialog.hide();

        }else if(result.Status==2){
            progressDialog.hide();

            JSON_GetJobList json_getJobList=new JSON_GetJobList(con,User);

           // DeviceAuthentication deviceAuthentication=new DeviceAuthentication();
            //deviceAuthentication.show_DeviceAuthentication(con);

        }else if(result.Status==3){
            er.setTextColor(0xFFCC0000);
            er.setGravity(Gravity.CENTER);
            er.setText(result.PassHash);
            progressDialog.hide();
        }
    }
    }


    private UserLogin deserialize_json(String input)
    {
        JSONObject jsonObject=null;
        try {
            jsonObject=new JSONObject(input);
            UserLogin userLogin=new UserLogin(jsonObject.getString("PassHash"),jsonObject.getString("ResourceId"),jsonObject.getString("TridentUserId"),jsonObject.getString("StrukturaId"),jsonObject.getString("UserId"),jsonObject.getString("Status"));
            return userLogin;
        } catch (JSONException e){
            return null;

        } catch (Exception e) {
            return null;
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
