package com.mathcity.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mathcity.myapplication.Adpters.YoutubeVideoAdapter;
import com.mathcity.myapplication.R;

public class Learning_List_Activity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_list);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        findViewById(R.id.view2).setVisibility(View.VISIBLE);

    }

    /**
     * populate the recyclerview and implement the click event here
     */


    private void generateDummyVideoList(String topic) {
        Toast.makeText(this, "topic" + topic, Toast.LENGTH_SHORT).show();
        //get the video id array, title array and duration array from strings.xml
        switch (topic) {
            case "Division":

                recyclerView.setAdapter(new YoutubeVideoAdapter(this, getResources().getStringArray(R.array.division)));
                break;


            case "Multiplication":
                recyclerView.setAdapter(new YoutubeVideoAdapter(this, getResources().getStringArray(R.array.multiply)));
                break;


            case "Addition":
                recyclerView.setAdapter(new YoutubeVideoAdapter(this, getResources().getStringArray(R.array.video_id_array)));
                break;


            case "Subtraction":
                recyclerView.setAdapter(new YoutubeVideoAdapter(this, getResources().getStringArray(R.array.substraction)));
                break;


            case "EvenOdd":
                recyclerView.setAdapter(new YoutubeVideoAdapter(this, getResources().getStringArray(R.array.evenodd)));
                break;


            case "ImageQuestion":
                recyclerView.setAdapter(new YoutubeVideoAdapter(this, getResources().getStringArray(R.array.img_ques)));
                break;

        }
        findViewById(R.id.view2).setVisibility(View.GONE);

        recyclerView.setVisibility(View.VISIBLE);
        backcount++;
    }

    int backcount = 1;

    public void back(View view) {
        Toast.makeText(this, "" + backcount, Toast.LENGTH_SHORT).show();
        if (backcount >= 2) {

            backcount--;
            recyclerView.setVisibility(View.GONE);
            findViewById(R.id.view2).setVisibility(View.VISIBLE);
        } else {
            finish();
        }
    }

    public void Go(View view) {

        switch (view.getId()) {

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

    private void selected(String topic) {
        generateDummyVideoList(topic);
    }
}