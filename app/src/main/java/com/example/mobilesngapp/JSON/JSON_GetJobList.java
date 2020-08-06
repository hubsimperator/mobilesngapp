package com.example.mobilesngapp.JSON;

import android.content.Context;
import android.os.AsyncTask;

import com.example.mobilesngapp.Activity.MainActivity;
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
        new JSON_GetJobList.HttpAsyncTask2().execute("https://notif2.sng.com.pl/api/Mobile2AppGetWorkList");

    }

    public JSON_GetJobList(Context con, String user, String sDate) {
        this.con = con;
        User = user;
        this.sDate = sDate;
        new JSON_GetJobList.HttpAsyncTask2().execute("https://notif2.sng.com.pl/api/Mobile2AppGetWorkList");

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
                    Job job=new Job(row.getString("WorkPlanId"), row.getString("WorkReqCode"), row.getString("WorkReqName"), row.getString("Location"),row.getString("workkind"), row.getString("Status"), row.getString("Progress"), row.getString("ObjectName"), row.getString("WorkDescription"), row.getString("ApproachDuration"), row.getString("WorkDuration"), row.getString("ResourceEiTID"), row.getString("WorkPlanTimeRangeID"), row.getString("pWorkPlan"), row.getString("WorkStart"), row.getString("WorkEnd"), row.getString("Real"), row.getString("ProblemReason"), row.getString("WorkReqId"), row.getString("ProblemReasonName"), row.getString("ResourceName"), row.getString("C"), row.getString("D"));
                    JobList.add(job);
            }

            } catch (JSONException e) {
                    e.printStackTrace();
                }
            return JobList;//deserialize_json(post);
        }


    @Override
    protected void onPostExecute(ArrayList<Job> result) {
        MainActivity mainActivity = new MainActivity();
        mainActivity.getDataFromJson(result);
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
