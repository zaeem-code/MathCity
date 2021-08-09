package com.mathcity.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mathcity.myapplication.R;

public class TestingDifficultySelection_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_section);
    }

    public void back(View view) {
        finish();
    }

    public void action(View view) {

        switch (view.getId()){
            case R.id.easy:

                show("Easy");
                break;
            case R.id.medium:
                show("Medium");
                break;
            case R.id.hard:
                show("Hard");
                break;

        }



    }
    private void show( String chk){

startActivity(new Intent(this,TopicActivity.class).putExtra("chk",chk));
finish();

    }
}