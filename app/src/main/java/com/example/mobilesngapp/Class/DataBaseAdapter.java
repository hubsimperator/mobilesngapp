package com.example.mobilesngapp.Class;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mobilesngapp.R;

import java.util.ArrayList;

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
        // pokolorowanie danych dla odpowiednich statusów

        if (job.StatusId == 501){
            workNameText.setBackgroundColor(Color.GREEN);
        }else if(job.StatusId == 550){
            workNameText.setBackgroundColor(Color.YELLOW);
        }else if (job.StatusId == 520){
            workNameText.setBackgroundColor(Color.GRAY);
        }
        workNameText.setText(job.Desig + " \n " + job.NBR);
        return convertView;
    }

}
