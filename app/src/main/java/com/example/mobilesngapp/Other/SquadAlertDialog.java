package com.example.mobilesngapp.Other;

/*
* Klasa ta będzie używana w momencie zmiany daty przez uzytkownika
* i w momencie gdy użytkownik kliknie na brygadę.
*
* Jeżeli użytkownik bedzie chciał zobaczyć skład brygady na obecny dzień
* to do JSONA zostanie wprowadzona pusta data, czyli zostanie zwrócony obecny dzień
*
* Jeżeli zostanie zmieniona, zostanie wywołana metoda z Tej klasy która przypisze do zmiennej
* typu STRING datę w formacie "yyyy-mm-dd".
*
* Jeżeli użytkownik będzie chciał  sprawdzić skład brygady z innej daty to w momencie
* kliknięcia na brygadę z zewnątrz zostanie przekazany "ResourceName".
* Zostanie uruchomiony json który dostanie datę ze zmiennej typu STRING i ResourceName,
* następnie
*
* */

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.mobilesngapp.Class.Brigade;
import com.example.mobilesngapp.JSON.JSON_GetSquad;

import java.util.ArrayList;

public class SquadAlertDialog extends DialogFragment {

    static String resourceName;
    static String date = "";
    static Context context;
    static public ArrayList<String> squadList = new ArrayList<String>();

    public Dialog onCreate(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (squadList != null){
            builder.setTitle(resourceName)
                    .setItems(squadList.toArray(new String[0]), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int i) {
                            // The 'i' argument contains the index position
                            // of the selected item
                        }
                    });
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    squadList.clear();
                }
            });
        }else{
            builder.setMessage("Brak danych.");
        }
        builder.show();
        return builder.create();
    }

    public String getResourceName(String result, Context c){
        resourceName = result;
        context = c;
        JSON_GetSquad json_getSquad = new JSON_GetSquad(c, resourceName, date);
        return resourceName;
    }

    public String getDateFromCalendar(String result){
        date = result;
        return date;
    }

    public ArrayList<String> squadResult(ArrayList<Brigade> result){
        for (int i = 0; i < result.size(); i++){
         squadList.add(result.get(i).getChildResourceName());
        }
        return squadList;
    }
}
