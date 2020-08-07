package com.example.mobilesngapp.Other;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mobilesngapp.Class.Brigade;
import com.example.mobilesngapp.Class.Job;
import com.example.mobilesngapp.JSON.JSON_GetSquad;
import com.example.mobilesngapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.mobilesngapp.R.layout.item_listview;

public class ArrayListAdapter extends ArrayAdapter<Job> {

    static public ArrayList<Brigade> squadList;


    public ArrayListAdapter(Context context, ArrayList<Job> jobList){
        super(context, 0, jobList);
    }

    public View getView(int position, View convertView, final ViewGroup parent){
        final Job job = getItem(position);

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

        squadTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSON_GetSquad json_getSquad = new JSON_GetSquad(getContext(), job.ResourceName);
                /*AlertDialog.Builder squadDialog = new AlertDialog.Builder(getContext());
                squadDialog.setTitle(job.ResourceName);
                convertedToDialogSquadList = squadList.toString();
                squadDialog.setItems(Integer.parseInt(convertedToDialogSquadList), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
                squadDialog.show();*/

               /* AlertDialog.Builder squadDialog = new AlertDialog.Builder(getContext());

                List<String> dialogList = null;
                
                if (squadList != null){
                    for (int i = 0; i <squadList.size(); i++){
                        dialogList.add(squadList.get(i).toString());
                    }
                }else {
                    dialogList.add("Brak danych.");
                }

                squadDialog.setTitle(job.ResourceName);
                squadDialog.setMessage((CharSequence) dialogList);
                squadDialog.show();*/
                AlertDialog.Builder squadDialog = new AlertDialog.Builder(getContext());
                squadDialog.setTitle(job.ResourceName);
                squadDialog.setMessage("TEST");
                squadDialog.show();
            }
        });
        return convertView;

    }
    //Metoda wywoływana przez json-a
    public ArrayList<Brigade> squadListView(ArrayList<Brigade> result){
        squadList = result;
        return squadList;
    }

}
