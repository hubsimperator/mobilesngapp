package com.example.mobilesngapp.Other;

import com.example.mobilesngapp.Class.Job;

import java.util.ArrayList;

public class JobListFilter {
    public ArrayList<Job> listOfJobs;

    public JobListFilter(ArrayList<Job> listOfJobs) {
        this.listOfJobs = listOfJobs;
    }

    public ArrayList<Job> filterByStatus(Integer status){
        // Status 0 - Nowe prace nierozpoczęte
        // Status 1 - Prace aktualne rozpoczęte
        // Status 2 - Prace skończone

        switch(status){
            case 0:
                // Plan Dnia
                status = 501;
                break;
            case 1:
                // Prace Aktualne
                status = 550;
                break;
            case 2:
                // Prace zakończone
                status = 520;
                break;

        }

        ArrayList<Job> filtredList = new ArrayList<>();

        for(int i = 0; i<listOfJobs.size(); i++){

            if (listOfJobs.get(i).StatusId.intValue()==status.intValue()){
                filtredList.add(listOfJobs.get(i));
            }else{
            }
        }
        return filtredList;
    }
}
