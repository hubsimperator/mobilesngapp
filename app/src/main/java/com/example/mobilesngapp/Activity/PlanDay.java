package com.example.mobilesngapp.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilesngapp.R;

public class PlanDay extends AppCompatActivity {

    ListView listView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workplan);
        String data[] = {};
        listView = findViewById(R.id.listView);
        textView = findViewById(R.id.textView);
        Adapter adapter = new Adapter(this, data);
        listView.setAdapter(adapter);

    }
    class Adapter extends ArrayAdapter<String>{
        Context context;
        String rdata[];

        Adapter(Context c, String mdara[]){
            super(c, R.layout.activity_row_workplan, R.id.textView);
            this.context = c;
            this.rdata = mdara;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.activity_row_workplan,parent,false);
            textView.setText(rdata[position]);
            return row;
        }
    }


}
