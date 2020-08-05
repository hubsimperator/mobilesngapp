package com.example.mobilesngapp.Other;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
Pozostało zaimplementowac metody do Activity przypisując je do aktywności buttonów onClick
---------
Sprawdzić czy nie trzeba w Manifeście nadać uprawnien do zpisywania i odczytywania bazy z pamięci telefonu
---------
Wyświetlanie bazy podpiąć pod text View
---------
Przetestować działanie bazy danych i funkcji takich jak update, insert
---------
Ustawić w momencie kiedy JSON pobiera dane z bazy servera to uruchamia metodę insert (zmiana daty w aplikacji, zobaczenie poprzednich dni pracy)
---------
Stworzyć JSON który po zakonczeniu dnia pracy będzie pobierał dane z lokalnej bazy danych do bazy online i ją aktualizował / lub ustawić aktualizację bazy
servera co każde użycie updateData
---------
Ustalić na podstawie danych z lokalnej bazy danych które prace jaki mają status i odpowiednio zaprezentować te dane
 */




public class SQLiteManager {

    dataBaseHelper dataBaseHelper;

    public SQLiteManager(Context context){
        dataBaseHelper = new dataBaseHelper(context);
    }

    public long insertData(Integer workPlanId, String workStart, String duration, String workName, String NBR, String desig, String jobStart, String jobEnd, String jobMinStart, String jobMaxStart, String SNGUser, String dzial, String status, Integer statusId){
        SQLiteDatabase databaseInsertData = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dataBaseHelper.WORK_PLAN_ID, workPlanId);
        contentValues.put(dataBaseHelper.WORK_START, workStart);
        contentValues.put(dataBaseHelper.DURATION, duration);
        contentValues.put(dataBaseHelper.WORK_NAME, workName);
        contentValues.put(dataBaseHelper.NBR, NBR);
        contentValues.put(dataBaseHelper.DESIG, desig);
        contentValues.put(dataBaseHelper.JOB_START, jobStart);
        contentValues.put(dataBaseHelper.JOB_END, jobEnd);
        contentValues.put(dataBaseHelper.JOB_MIN_START, jobMinStart);
        contentValues.put(dataBaseHelper.JOB_MAX_START, jobMaxStart);
        contentValues.put(dataBaseHelper.SNG_USER, SNGUser);
        contentValues.put(dataBaseHelper.DZIAL, dzial);
        contentValues.put(dataBaseHelper.STATUS, status);
        contentValues.put(dataBaseHelper.STATUS_ID, statusId);
        long id = databaseInsertData.insert(dataBaseHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public String getData(){
        SQLiteDatabase databaseGetData = dataBaseHelper.getWritableDatabase();
        String [] databaseColumns = {dataBaseHelper.ID, dataBaseHelper.WORK_PLAN_ID, dataBaseHelper.WORK_START, dataBaseHelper.DURATION, dataBaseHelper.WORK_NAME,
        dataBaseHelper.NBR, dataBaseHelper.DESIG, dataBaseHelper.JOB_START, dataBaseHelper.JOB_END, dataBaseHelper.JOB_MIN_START, dataBaseHelper.JOB_MAX_START,
        dataBaseHelper.SNG_USER, dataBaseHelper.DZIAL, dataBaseHelper.STATUS, dataBaseHelper.STATUS_ID};
        Cursor cursor = databaseGetData.query(dataBaseHelper.TABLE_NAME, databaseColumns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext()){
            int columnId = cursor.getInt(cursor.getColumnIndex(dataBaseHelper.ID));
            int WorkPlanId = cursor.getInt(cursor.getColumnIndex(dataBaseHelper.WORK_PLAN_ID));
            String WorkStart = cursor.getString(cursor.getColumnIndex(dataBaseHelper.WORK_START));
            String Duration = cursor.getString(cursor.getColumnIndex(dataBaseHelper.DURATION));
            String WorkName = cursor.getString(cursor.getColumnIndex(dataBaseHelper.WORK_NAME));
            String NBR = cursor.getString(cursor.getColumnIndex(dataBaseHelper.NBR));
            String Desig = cursor.getString(cursor.getColumnIndex(dataBaseHelper.DESIG));
            String JobStart = cursor.getString(cursor.getColumnIndex(dataBaseHelper.JOB_START));
            String JobEnd = cursor.getString(cursor.getColumnIndex(dataBaseHelper.JOB_END));
            String JobMinStart = cursor.getString(cursor.getColumnIndex(dataBaseHelper.JOB_MIN_START));
            String JobMaxStart = cursor.getString(cursor.getColumnIndex(dataBaseHelper.JOB_MAX_START));
            String SNGUser = cursor.getString(cursor.getColumnIndex(dataBaseHelper.SNG_USER));
            String Dzial = cursor.getString(cursor.getColumnIndex(dataBaseHelper.DZIAL));
            String Status = cursor.getString(cursor.getColumnIndex(dataBaseHelper.STATUS));
            int StatusId = cursor.getInt(cursor.getColumnIndex(dataBaseHelper.STATUS_ID));

            buffer.append(columnId + "  " + WorkPlanId + "  " + WorkStart + "  " + Duration + "  " + WorkName
                    + "  " + NBR + "  " + Desig + "  " + JobStart + "  " + JobEnd + "  " + JobMinStart
                    + "  " + JobMaxStart + "  " + SNGUser + "  " + Dzial + "  " + Status + "  " + StatusId + " \n" );
        }
        return buffer.toString();
    }

    public int updateData(String jobStart, String newJobStart, String jobEnd, String newJobEnd, String status, String newStatus, String statusId , String newStatusId){
        //
        SQLiteDatabase databaseUpdateData = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String [] whereOldArgs = {jobStart, jobEnd, status, statusId};
        contentValues.put(dataBaseHelper.JOB_START, newJobStart);
        contentValues.put(dataBaseHelper.JOB_END, newJobEnd);
        contentValues.put(dataBaseHelper.STATUS, newStatus);
        contentValues.put(dataBaseHelper.STATUS_ID, newStatusId);
        int changer = databaseUpdateData.update(dataBaseHelper.TABLE_NAME, contentValues, null, whereOldArgs);
        return changer;
    }

    static class dataBaseHelper extends SQLiteOpenHelper{
        public static final String DATABASE_NAME = "mobilesngapp.db";
        public static final String TABLE_NAME = "Works";
        public static final String ID = "_id";
        public static final String WORK_PLAN_ID = "WorkPlanId";
        public static final String WORK_START = "WorkStart";
        public static final String DURATION = "Duration";
        public static final String WORK_NAME = "WorkName";
        public static final String NBR = "NBR";
        public static final String DESIG = "Desig";
        public static final String JOB_START = "JobStart";
        public static final String JOB_END = "JobEnd";
        public static final String JOB_MIN_START = "JobMinStart";
        public static final String JOB_MAX_START = "JobMaxStart";
        public static final String SNG_USER = "SNGUser";
        public static final String DZIAL = "Dzial";
        public static final String STATUS = "Status";
        public static final String STATUS_ID = "StatusId";
        public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ""+WORK_PLAN_ID+" TEXT NOT NULL, " +
                ""+WORK_START+" TEXT NOT NULL, "+DURATION+" TEXT NOT NULL, "+WORK_NAME+" TEXT NOT NULL, "+NBR+" TEXT NOT NULL, "+DESIG+" TEXT NOT NULL, " +
                ""+JOB_START+" TEXT NOT NULL, "+JOB_END+" TEXT NOT NULL, "+JOB_MIN_START+" TEXT, "+JOB_MAX_START+" TEXT NOT NULL, " +
                ""+SNG_USER+" TEXT NOT NULL, "+DZIAL+" TEXT NOT NULL, "+STATUS+" TEXT NOT NULL, "+STATUS_ID+" INTEGER NOT NULL)";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;
        public static final int version = 1;

        //konstruktor SQLiteManager
        public dataBaseHelper(Context context){
            super(context, DATABASE_NAME, null, version);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try {
                sqLiteDatabase.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }
        }


        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldDataBase, int newDataBase) {
            try{
                System.out.println("On upgrade:" + context);
                sqLiteDatabase.execSQL(DROP_TABLE);
                onCreate(sqLiteDatabase);
            }catch (Exception e){
                System.out.println("Error " + e.getMessage());
            }
        }
    }

}
