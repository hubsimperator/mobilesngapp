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
    private static int Position;

    public ViewPagerAdapter(Context context, List<String> numberOfScreens, ViewPager2 viewPager2, ArrayList<Job> jobList, int position){
        this.mInflater = LayoutInflater.from(context);
        this.mNumberOfScreens = numberOfScreens;
        this.viewPager2 = viewPager2;
        this.c = context;
        this.jobList = jobList;
        Position = position;
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

            /*Stworzona zmienna "Position" do filtracji danych z bazy. Maxymalny rozmiar Position to: 2
            * getItemCount() zwraca: 3 <- zależne od ilości stron w viewPager czyli od listy w MainActivity.
            * za każdym wywolaniem getItemCount() odejmujemy od tego -2, a position jest powiększane o 1.
            * Jeżeli dodamy więcej kart należy odjąć od getItemCount liczbę ekranów winikającej z numberOfPages -1.
            * */
            Position += getItemCount() - 2;
            showJobListView(jobList, Position);

        }
        public void showJobListView(ArrayList<Job> jobList, int position){
            JobListFilter filter = new JobListFilter(jobList);
            ArrayListAdapter adapter = new ArrayListAdapter(c, filter.filterByStatus(position));
            jobListView.setAdapter(adapter);
        }
    }
}
