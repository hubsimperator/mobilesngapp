package com.example.mobilesngapp.Class;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mobilesngapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.mobilesngapp.R.layout.item_workplan;

public class DataBaseAdapter extends ArrayAdapter<Job> {

    public DataBaseAdapter(Context context, ArrayList<Job> jobList) {
        super(context, 0, jobList);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        Job job = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(item_workplan, parent, false);
        }
        TextView workNameText = (TextView) convertView.findViewById(R.id.dataBaseTextView);
        TextView startTimeWorkTextView = (TextView) convertView.findViewById(R.id.startTimeWorkTextView);
        TextView endTimeWorkTextView = (TextView) convertView.findViewById(R.id.endTimeWorkTextView);
        TextView squadTextView = (TextView) convertView.findViewById(R.id.squadTextView);
        //String delimiter = "\\s+";
        String startJob = job.JobMinStart;
        String endJob = job.JobEnd;
        Date startJobDate = null;
        try {
            startJobDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startJob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String timeStartJob = new SimpleDateFormat("HH:mm").format(startJobDate);

        Date endJobDate = null;
        try {
            endJobDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endJob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String timeEndJob = new SimpleDateFormat("HH:mm").format(endJobDate);

        // pokolorowanie danych dla odpowiednich statusów

        if (job.StatusId == 501){
            workNameText.setBackgroundColor(Color.GREEN);
            /*startTimeWorkTextView.setBackgroundColor(Color.GREEN);
            endTimeWorkTextView.setBackgroundColor(Color.GREEN);
            squadTextView.setBackgroundColor(Color.GREEN);*/
        }else if(job.StatusId == 550){
            workNameText.setBackgroundColor(Color.YELLOW);
        }else if (job.StatusId == 520){
            workNameText.setBackgroundColor(Color.RED);
        }
        startTimeWorkTextView.setText(timeStartJob);
        endTimeWorkTextView.setText(timeEndJob);
        workNameText.setText(job.Desig);
        squadTextView.setText(job.NBR);
        return convertView;
    }

}
