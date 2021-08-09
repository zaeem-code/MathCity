package com.mathcity.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mathcity.myapplication.R;
import com.mathcity.myapplication.Utills.Common;
import com.mathcity.myapplication.model.parents;
import com.mathcity.myapplication.model.profile;

import org.jetbrains.annotations.NotNull;

public class ParentsActivity extends AppCompatActivity {
EditText name,email;
    parents items=new parents();
    FirebaseDatabase database;
    DatabaseReference myRef;profile profielItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_parents);


        name=findViewById(R.id.name);
        email=findViewById(R.id.email);

        getDetails(this);


    }

    public void back(View view) {
        finish();
    }

    public void save(View view) {
        if (TextUtils.isEmpty(email.getText().toString().trim())){
            email.setError("Invalid Email");
        }else if (TextUtils.isEmpty(name.getText().toString().trim())){
            name.setError("Name can't be empty");
        }else if (!profielItem.getUid().isEmpty()){
            myRef.child(profielItem.getUid().trim().substring(0,10));
            items.setEmail(email.getText().toString().trim());
            items.setName(name.getText().toString().trim());

            myRef.setValue(items);
            new Common(this).Setparentsdetais(items);
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "In order to save details Register Yourself first", Toast.LENGTH_SHORT).show();
        }
    }
    public void getDetails(Context context){

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("ParentsDetails");
        profielItem=new Common(context).GetProfile();

        if (!profielItem.getUid().isEmpty()) {
            myRef.child(profielItem.getUid().trim().substring(0,10));

            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        items = snapshot.getValue(parents.class);
                        if (items != null) {
                            try {

                                name.setText(items.getName());
                                email.setText(items.getEmail());
                            }catch (Exception e){

                            }

                            new Common(context).Setparentsdetais(items);
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });

        }
    }
}