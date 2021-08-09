package com.mathcity.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mathcity.myapplication.R;
import com.mathcity.myapplication.Utills.Common;
import com.mathcity.myapplication.model.profile;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
TextView name,email,id;    CircleImageView dp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name=findViewById(R.id.name);
        id=findViewById(R.id.id);
        dp=findViewById(R.id.dp);
        email=findViewById(R.id.email);
        profile profielItem=new Common(this).GetProfile();

        if (! profielItem.getPic().isEmpty()){
        name.setText(profielItem.getName());
        id.setText(profielItem.getUid());
        email.setText(profielItem.getEmail());
            Glide.with(this).load(profielItem.getPic())
                    .fitCenter().into(dp); }else {
            name.setText    ("Name :Gust");
            email.setText   ("Email:NA");
            id.setText      ("ID:Guest11209");
        }
    }

    public void back(View view) {
        finish();
    }
}