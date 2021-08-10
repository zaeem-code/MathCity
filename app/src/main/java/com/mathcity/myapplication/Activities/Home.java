package com.mathcity.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mathcity.myapplication.BuildConfig;
import com.mathcity.myapplication.R;
import com.mathcity.myapplication.Utills.Common;
import com.mathcity.myapplication.model.parents;
import com.mathcity.myapplication.model.profile;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Bundle savedInstanceState;DrawerLayout drawer;
    Common common;
    profile profielItem=new profile();
    CircleImageView dp; TextView name,email;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigationnrawerlayout);
        this.savedInstanceState = savedInstanceState;
          common= new Common(this);
        DrawerSetup();

        new ParentsActivity().getDetails(this);

    }

    public void testing(View view)
    {

        startActivity(new Intent(this, TestingDifficultySelection_Activity.class));
    }

    public void learning(View view)
    {
        startActivity(new Intent(this, Learning_List_Activity.class));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item)
    {

//        item.setChecked(true);
        int id = item.getItemId();
        if (id == R.id.logout){
            logut();

        } else if (id == R.id.testingsection) {

            startActivity(new Intent(this, TestingDifficultySelection_Activity.class));

        } else if (id == R.id.learninsection) {
            startActivity(new Intent(this, Learning_List_Activity.class));

        } else if (id == R.id.profile) {
            startActivity(new Intent(this, ProfileActivity.class));

        }else if (id == R.id.records) {
            startActivity(new Intent(this, recordsActivity.class));

        }else if (id == R.id.parents) {
            startActivity(new Intent(this, ParentsActivity.class));

        }
        else if (id == R.id.share) {

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        }
        else if (id==R.id.feedback)
        {

            sendEmail();


        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    protected void sendEmail() {
        String[] TO = {"F2017065156@umt.edu.pk"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
        }
    }

    private void DrawerSetup()
    {

            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
          navigationView = (NavigationView) findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, findViewById(R.id.tolbar), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        navigationView.bringToFront();
        View hView =  navigationView.getHeaderView(0);

        dp=hView.findViewById(R.id.dp);
        name=hView.findViewById(R.id.name);
        email=hView.findViewById(R.id.email);
    }

    public void closeDrawer(View view)
    {
        drawer.closeDrawer(GravityCompat.START);

    }

    private void logut()
    {

            FirebaseAuth firebaseAuth;
            FirebaseAuth.AuthStateListener authStateListener;

            authStateListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                    // Get signedIn user
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                }
            };
            firebaseAuth = com.google.firebase.auth.FirebaseAuth.getInstance();


                if (authStateListener != null) {
                    FirebaseAuth.getInstance().signOut();
                    firebaseAuth.addAuthStateListener(authStateListener);
                    Toast.makeText(this, "Loged Out", Toast.LENGTH_SHORT).show();

                    profielItem.setEmail("");
                    profielItem.setName("Guest");
                    profielItem.setUid("");
                    profielItem.setPic("");
                    // you can store user data to SharedPreference
                    common.SetProfile(profielItem);
                    common. SetSigning("false");
                    common.SetTotalAttempts(0);
                    common.SetTotalAttemptsFAILL(0);
                    common.SetTotalAttemptsPASS(0);
                    common.SetTotalScore(0);
                    common.SetBonusScore(0);
                    parents p=new parents();
                    p.setName("");
                    p.setName("");
                    common.Setparentsdetais(p);
                }

                startActivity(new Intent(this,register.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();

        }

    @Override
    protected void onStart()
    {
        super.onStart();
        profielItem= common.GetProfile();
        if (! profielItem.getPic().isEmpty()){
        Glide.with(this).load(profielItem.getPic()).into(dp);
        name.setText(profielItem.getName());
        email.setText(profielItem.getEmail());}else {
            name.setText("Loged in as:");
            email.setText("Guest");
        }
        permission();
    }
    private void permission()
    {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);
    }
}
