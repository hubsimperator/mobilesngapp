package com.example.mobilesngapp.Other;

import android.app.Activity;

import com.example.mobilesngapp.Class.Job;

import java.util.ArrayList;

public class JobListFilter extends Activity {
    public ArrayList<Job> listOfJobs;

    public JobListFilter(ArrayList<Job> listOfJobs) {
        this.listOfJobs = listOfJobs;
    }

    public ArrayList<Job> filterByStatus(Integer page){
        /*Page 0 - Wszystkie prace
        * Page 1 - Prace rozpoczęte
        * Page 2 - Prace zakończone
        * Każdy page to strona w ViewPager, jeżeli zmienimy ilosć stron, i chcemy na nowych stronach dodać filtrację danych to należy
        * dodać kolejnego case.* */

        String result = "";

        switch(page){
            case 0:
                // Plan Dnia
                break;
            case 1:
                // Prace Aktualne
                result = "RUN";
                break;
            case 2:
                // Prace zakończone
                result = "DON";
                break;
            default:
                break;
        }

        ArrayList<Job> filteredList = new ArrayList<>();
        if(listOfJobs != null){
            for(int i = 0; i<listOfJobs.size(); i++){
                if (result.equals("")){
                    filteredList.add(listOfJobs.get(i));
                }else if (listOfJobs.get(i).Status.equals(result)){
                    filteredList.add(listOfJobs.get(i));
                }else{

                }
            }
        }

        return filteredList;
    }

}
