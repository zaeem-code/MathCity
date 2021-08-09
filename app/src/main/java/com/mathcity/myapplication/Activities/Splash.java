package com.mathcity.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.mathcity.myapplication.R;
import com.mathcity.myapplication.Utills.Common;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(new Common(getApplicationContext()).GetSigning().equals("true")){
                    startActivity(new Intent(getApplicationContext(),Home.class));
                    finish();
                }else {
                startActivity(new Intent(getApplicationContext(),register.class));
                finish();}
            }
        }, 2000);
    }
}