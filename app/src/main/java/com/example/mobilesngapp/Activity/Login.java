package com.example.mobilesngapp.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.mobilesngapp.JSON.JSON_Login;
import com.example.mobilesngapp.Other.getNetworkType;
import com.example.mobilesngapp.R;


public class Login extends AppCompatActivity {
    /** Poniżej wartość Wersji aplikacji - musi być zgodna z wartością dostępną w procedurze cs.pCheckVersion
     * w celu wypuszczenia nowej wersji oprogramowania zmieniamy poniższą wartosć na nową, aplikację generujemy i wystawiamy w nc.sng.com.pl
     * i w wyżej wspomnianej procedurze wprowadzamy nową wartosć w wskazane miejsce*/
    String WersjaAplikacji = "Wersja Alfa8";

    EditText Password;


    private BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //setContentView(R.layout.activity_login);
            final TextView error = (TextView) findViewById(R.id.errortxt);
            if (isConnected()) {
                error.setTextColor(0xFF00CC00);
                getNetworkType gnt = new getNetworkType();
                error.setGravity(Gravity.RIGHT);
                error.setText("sieć: " + gnt.getNetworkType(getApplicationContext()).toString());
            } else {
                error.setTextColor(0xFFFF0000);
                error.setText("Błąd połączenia, sprawdź połączenie z internetem");
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(networkChangeReceiver);
    }

    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (!checkPermissions()) {
            setPermissions();
        }
//zmiana huberta

        Password = (EditText) findViewById(R.id.Passwordtxt);


//przypal na mnie
        if (isConnected()) {

            final TextView error = (TextView) findViewById(R.id.errortxt);
            final Context context = this;
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            String tit = null;
            String bod = null;


            if (isConnected()) {
                error.setTextColor(0xFF00CC00);
                getNetworkType gnt = new getNetworkType();
                error.setGravity(Gravity.RIGHT);
                error.setText("sieć: " + gnt.getNetworkType(this).toString());
            } else {
                error.setTextColor(0xFFFF0000);
                error.setText("Błąd połączenia, sprawdź połączenie z internetem");
            }


            final TextView poka = (TextView) findViewById(R.id.poka);
            poka.setVisibility(View.INVISIBLE);

            ((EditText) findViewById(R.id.Passwordtxt)).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    if (((EditText) findViewById(R.id.Passwordtxt)).length() == 0) {
                        poka.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (((EditText) findViewById(R.id.Passwordtxt)).getText().length() == 0) {
                        poka.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (((EditText) findViewById(R.id.Passwordtxt)).getText().length() == 0) {
                        poka.setVisibility(View.VISIBLE);
                    }
                }
            });
            poka.setOnTouchListener(show_text);

            ImageView zaloguj = (ImageView) findViewById(R.id.Loginbtn);


            zaloguj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final ProgressDialog pg = new ProgressDialog(context);
                    pg.setMessage("Wczytywanie...");
                    pg.show();
                    final String Logi = ((EditText) findViewById(R.id.Logintxt)).getText().toString();
                    final String Haslo = ((EditText) findViewById(R.id.Passwordtxt)).getText().toString();


                    TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    String IMEI ="";
                    try {
                        IMEI = telephonyManager.getDeviceId();
                    }catch (NullPointerException ne){


                    Log.d("a","aa");
                    }

                    if(IMEI==null){
                        IMEI = Settings.Secure.getString(
                                Login.this.getContentResolver(),
                                Settings.Secure.ANDROID_ID);
                    }

                    String telephoneNumber ="telefon";

                    try {
                        telephoneNumber = telephonyManager.getLine1Number();
                    }catch (Exception e){
                        Log.d("a","aa");
                    }

                if (Logi.equals("") || Haslo.equals("")) {
                    error.setTextColor(0xFFCC0000);
                    error.setGravity(Gravity.CENTER);
                    error.setText("Login i Hasło nie mogą być puste");
                    pg.hide();
                } else {

                    JSON_Login json_login=new JSON_Login();
                    json_login.StartUpdate(Logi,Haslo,IMEI,telephoneNumber,Login.this,pg,error);

                }
            }
        });
    }else {TextView error = (TextView) findViewById(R.id.errortxt);
            w8(error);
        }

}

    private boolean checkPermissions() {

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.VIBRATE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
              if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SET_ALARM) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        return true;
    }

    private void setPermissions() {
        ActivityCompat.requestPermissions((Activity) this, new String[]{
                Manifest.permission.INTERNET , Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.VIBRATE ,Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.SET_ALARM,Manifest.permission.READ_PHONE_STATE}, 1);
    }

    public void w8(final TextView error){
        try {
            error.setTextColor(0xFFFF0000);
            error.setText("Błąd połączenia, sprawdź połączenie z internetem");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(isConnected()) {
                        finish();
                        startActivity(getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    }
                    else
                    {
                        w8(error);
                    }
                }
            }, 1000);

        }catch(Exception e){
        }
    }

    public static int backButtonCount;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed()
    {
        new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                backButtonCount=1;
            }
            public void onFinish() {
                Log.d("Zegar","done");
                backButtonCount=0;
            }

        }.start();
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "W celu zamknięcia aplikacji naćiśnij POWRÓT jeszcze raz", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
    private View.OnTouchListener show_text = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (v.getId() == R.id.poka) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                return true;
            }
            return false;
        }
    };

}


