package com.example.mobilesngapp.Other;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mobilesngapp.Class.Job;
import com.example.mobilesngapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.mobilesngapp.R.layout.item_listview;

public class ArrayListAdapter extends ArrayAdapter<Job> {

    public ArrayListAdapter(Context context, ArrayList<Job> jobList){
        super(context, 0, jobList);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Job job = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(item_listview, parent, false);
        }
        TextView workNameText = (TextView) convertView.findViewById(R.id.descriptionOfWorkTextView);
        TextView startTimeWorkTextView = (TextView) convertView.findViewById(R.id.startTimeWorkTextView);
        TextView endTimeWorkTextView = (TextView) convertView.findViewById(R.id.endTimeWorkTextView);
        final TextView squadTextView = (TextView) convertView.findViewById(R.id.squadTextView);
        String startJob = job.WorkStart;
        String endJob = job.WorkEnd;
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

        if (job.Status.equals("PLA")){
            workNameText.setBackgroundColor(Color.GREEN);
        }else if(job.Status.equals("RUN")){
            workNameText.setBackgroundColor(Color.YELLOW);
        }else if (job.Status.equals("DON")){
            workNameText.setBackgroundColor(Color.GRAY);
        }
        startTimeWorkTextView.setText(timeStartJob);
        endTimeWorkTextView.setText(timeEndJob);
        workNameText.setText(job.WorkReqName);
        squadTextView.setText(job.ResourceName);
        return convertView;

    }

}
