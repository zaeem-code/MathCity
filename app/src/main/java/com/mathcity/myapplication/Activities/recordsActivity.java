package com.mathcity.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.mathcity.myapplication.R;
import com.mathcity.myapplication.Utills.Common;

public class recordsActivity extends AppCompatActivity {
TextView totalattempts,passed,totalscore,bonus,weakareas,stronareas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        totalscore=findViewById(R.id.totalscore);
        passed=findViewById(R.id.pass);
        totalattempts=findViewById(R.id.attempt);
        weakareas=findViewById(R.id.wa);
        stronareas=findViewById(R.id.st);
        bonus=findViewById(R.id.bonus);
        Common common=new Common(this);


        totalattempts.setText(String.valueOf(common.GetTotalAttempts()));
        if (!TextUtils.isEmpty(common.GetStrongAreas())){
        stronareas.setText(String.valueOf(common.GetStrongAreas()));}

        if (!TextUtils.isEmpty(common.GetWeakAreas())){
        weakareas.setText(String.valueOf(common.GetWeakAreas()));}
        passed.setText(String.valueOf(common.GetTotalAttemptsPASS()));
        totalscore.setText(String.valueOf(common.GetTotalScore()));
        bonus.setText(String.valueOf(common.GetBonusScore()));
    }

    public void back(View view) {
        finish();
    }
}