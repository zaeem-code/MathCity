package com.mathcity.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.mathcity.myapplication.Dialog.Dialog_testing_Fragment;
import com.mathcity.myapplication.R;

public class TopicActivity extends AppCompatActivity {
String chk="Easy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        chk=getIntent().getStringExtra("chk");
    }

    public void back(View view)
    {
        finish();
    }

    public void Go(View view)
    {
        switch (view.getId())
        {
            case R.id.div:
                selected("Division");
                break;

            case R.id.mul:
                selected("Multiplication");
                break;

            case R.id.add:
                selected("Addition");
                break;

            case R.id.sub:
                selected("Subtraction");
                break;

            case R.id.evn:
                selected("EvenOdd");
                break;

            case R.id.anl:
                selected("ImageQuestion");
                break;
        }
    }
    private void selected(String topic)
    {

        Dialog_testing_Fragment dialogFragment;
        dialogFragment= new Dialog_testing_Fragment(chk,topic.trim(),TopicActivity.this);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        dialogFragment.show(ft, "dialog");
    }
}