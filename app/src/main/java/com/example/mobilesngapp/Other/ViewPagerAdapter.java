package com.example.mobilesngapp.Other;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mobilesngapp.Class.Job;
import com.example.mobilesngapp.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    private List<String> mNumberOfScreens;
    private LayoutInflater mInflater;
    private ViewPager2 viewPager2;
    private ArrayList<Job> jobList;
    Context c;

    public ViewPagerAdapter(Context context, List<String> numberOfScreens, ViewPager2 viewPager2, ArrayList<Job> jobList){
        this.mInflater = LayoutInflater.from(context);
        this.mNumberOfScreens = numberOfScreens;
        this.viewPager2 = viewPager2;
        this.c = context;
        this.jobList = jobList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_viewpager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mNumberOfScreens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout relativeLayout;
        ListView jobListView;

        ViewHolder(View itemView){
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.container);
            jobListView = itemView.findViewById(R.id.jobListView);

            showJobListView(jobList);

        }
        public void showJobListView(ArrayList<Job> jobList){
            ArrayListAdapter adapter = new ArrayListAdapter(c, jobList);
            jobListView.setAdapter(adapter);
        }
    }
}
