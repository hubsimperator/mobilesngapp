package com.example.mobilesngapp.JSON;

import android.content.Context;
import android.os.AsyncTask;

import com.example.mobilesngapp.Class.Brigade;

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

public class JSON_GetSquad {

    Context con = null;
    public String ResourceName;
    public String sDate;

    ArrayList<Brigade> squadList=new ArrayList<>();

    public JSON_GetSquad(Context con, String brigade) {
        this.con = con;
        ResourceName = brigade;
        this.sDate = "";
        new JSON_GetSquad.HttpAsyncTask2().execute("https://notif2.sng.com.pl/api/Mobile2AppGetSkladBrygady");

    }


    public String POST(String url) {
        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            String json = "";
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("BrygadaName",ResourceName);
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
    private class HttpAsyncTask2 extends AsyncTask<String, Void, ArrayList<Brigade>> {

        @Override
        protected ArrayList<Brigade> doInBackground(String... urls) {
            String post= POST(urls[0]);
            JSONArray array = null;

            try {
                array = new JSONArray(post);
                for(int i =0; i<array.length();i++){
                    JSONObject row = array.getJSONObject(i);
                    Brigade squad=new Brigade(row.getString("ChildResourceName"));
                    squadList.add(squad);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return squadList;//deserialize_json(post);
        }


        @Override
        protected void onPostExecute(ArrayList<Brigade> result) {

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
