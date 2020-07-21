package com.example.mobilesngapp.JSON;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.mobilesngapp.Class.Job;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JSON_GetJobList {

    Context con = null;
    public String User;
    public String sDate;

    ArrayList<Job> JobList=new ArrayList<>();

    public JSON_GetJobList(Context con, String user) {
        this.con = con;
        User = user;
        this.sDate = "";
        new JSON_GetJobList.HttpAsyncTask2().execute("https://notif2.sng.com.pl/api/Mobile2AppGetJobList");

    }

    public JSON_GetJobList(Context con, String user, String sDate) {
        this.con = con;
        User = user;
        this.sDate = sDate;
        new JSON_GetJobList.HttpAsyncTask2().execute("https://notif2.sng.com.pl/api/Mobile2AppGetJobList");

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
            jsonObject.accumulate("sDate",sDate);
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
    private class HttpAsyncTask2 extends AsyncTask<String, Void, ArrayList<Job>> {

        @Override
        protected ArrayList<Job> doInBackground(String... urls) {
            String post= POST(urls[0]);
            JSONArray array = null;

            try {
                array = new JSONArray(post);
                for(int i =0; i<array.length();i++){
                    JSONObject row = array.getJSONObject(i);
                    Job job=new Job(row.getInt("WorkPlanId"),row.getString("WorkStart"),row.getString("Duration"),row.getString("WorkName"),row.getString("NBR"),row.getString("Desig"),row.getString("JobStart"),row.getString("JobEnd"),row.getString("JobMinStart"),row.getString("JobMaxStart"),row.getString("SNGUser"),row.getString("Dzial"),row.getString("Status"),row.getInt("StatusId"));
                    JobList.add(job);
            }

            } catch (JSONException e) {
                    e.printStackTrace();
                }
            return JobList;//deserialize_json(post);
        }


    @Override
    protected void onPostExecute(ArrayList<Job> result) {

            for (int i = 0; i < result.size(); i++) {
                Log.d("Test",result.get(i).WorkName);
            }
            //przejsc do nowego okna i w liscie wypisac WorkName
        Intent intent = new Intent();
        startActivityForResult(intent, 0);
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
