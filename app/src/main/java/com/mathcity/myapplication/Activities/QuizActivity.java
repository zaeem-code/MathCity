package com.mathcity.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mathcity.myapplication.R;
import com.mathcity.myapplication.model.QuizModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class QuizActivity extends AppCompatActivity {

    int correct = 0, wrong = 0, current = 0, total = 9;
    String chk = "Easy", topic = "Addition";
    ProgressDialog dialog;
    QuizModel quizModel_item;
    ImageView Questionimg, optionaPic, optionbPic;


    TextView correctv, wrongtv, currenttv, Header, optiona, optionb, timer;
    ArrayList<QuizModel> PopulatedArray = new ArrayList();
//    ArrayList<QuizModel> pictureQuizModel_Array=new ArrayList();
//    ArrayList<QuizModel> quizModel_Addition_Array =new ArrayList();
//    ArrayList<QuizModel> quizModel_Subtraction_Array =new ArrayList();
//    ArrayList<QuizModel> quizModel_EvenOdd_Array =new ArrayList();
//    ArrayList<QuizModel> quizModel_Division_Array =new ArrayList();
    //fetch all the above from DB

    RelativeLayout headermain;

    ArrayList<QuizModel> Final_TEN_Quiz = new ArrayList();
    //will contain Final but non repetitive Qs

    Random rI = new Random();
    // genrates random value

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_quiz);
        chk = getIntent().getStringExtra("chk");
        topic = getIntent().getStringExtra("topic").trim();
//
        headermain = findViewById(R.id.header);


        ////
        ShowDialog("Loading Quiz ...");

///

        Questionimg = findViewById(R.id.Question);

        optionaPic = findViewById(R.id.optionaPic);
        optionbPic = findViewById(R.id.optionbPic);

        optiona = findViewById(R.id.optiona);
        optionb = findViewById(R.id.optionb);
////

        correctv = findViewById(R.id.correct);
        timer = findViewById(R.id.timer);
        wrongtv = findViewById(R.id.wrong);
        currenttv = findViewById(R.id.current);
        Header = findViewById(R.id.quiztitel);

        Header.setText(chk);
        switch (chk) {

            case "Medium":

                headermain.setBackgroundColor(Color.parseColor("#ffa500"));
                break;

            case "Hard":
                headermain.setBackgroundColor(Color.RED);
                break;
        }


//        POPULATING_PictureQuizModelARRAY();

        Populating_Array();
    }


    /// old methode
//    private void POPULATING_PictureQuizModelARRAY()
//    {
//        Log.d("hassan","---"+chk);
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference(chk).child("ImageQuestion");
//myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//    @Override
//    public void onDataChange(DataSnapshot dataSnapshot) {
//        if (!dataSnapshot.exists()){
//            Toast.makeText(QuizActivity.this, "Db1 is Empty", Toast.LENGTH_SHORT).show();
//            ShowDialog("");
//            return;
//        }
//        for (DataSnapshot dsp:dataSnapshot.getChildren())
//        {
//            quizModel_item=   dsp.getValue(QuizModel.class);
//            pictureQuizModel_Array.add(quizModel_item);
//
//        }
//
//        POPULATING_NormalQuizModel_Addition();
//    }
//
//    @Override
//    public void onCancelled(DatabaseError databaseError) {
//        Toast.makeText(QuizActivity.this, "InterIet connection isn't stable!", Toast.LENGTH_SHORT).show();
//        ShowDialog("");
//    }
//});
//
//    }
//
//    private void POPULATING_NormalQuizModel_Addition()
//    {
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference(chk).child("Addition");
//        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (!dataSnapshot.exists()){
//                    Toast.makeText(QuizActivity.this, "Db2 is Empty", Toast.LENGTH_SHORT).show();
//                 ShowDialog("");
//                    return;
//                }
//                for (DataSnapshot dsp:dataSnapshot.getChildren())
//                {
//                    quizModel_item =   dsp.getValue(QuizModel.class);
//                    quizModel_Addition_Array.add(quizModel_item);
//
//                }
//                POPULATING_NormalQuizModel_Division();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                ShowDialog("");
//            }
//        });
//
//    }
//
//    private void POPULATING_NormalQuizModel_Division()
//    {
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference(chk).child("Division");
//        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (!dataSnapshot.exists()){
//                    Toast.makeText(QuizActivity.this, "Db3 is Empty", Toast.LENGTH_SHORT).show();
//                    ShowDialog("");
//                    return;
//                }
//                for (DataSnapshot dsp:dataSnapshot.getChildren())
//                {
//                    quizModel_item =   dsp.getValue(QuizModel.class);
//                    quizModel_Division_Array.add(quizModel_item);
//
//                }
//                POPULATING_NormalQuizModel_EvenOdd();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                ShowDialog("");
//            }
//        });
//
//    }
//
//    private void POPULATING_NormalQuizModel_EvenOdd()
//    {
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference(chk).child("EvenOdd");
//        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (!dataSnapshot.exists()){
//                    Toast.makeText(QuizActivity.this, "Db4 is Empty", Toast.LENGTH_SHORT).show();
//                    ShowDialog("");
//                    return;
//                }
//                for (DataSnapshot dsp:dataSnapshot.getChildren())
//                {
//                    quizModel_item =   dsp.getValue(QuizModel.class);
//                    quizModel_EvenOdd_Array.add(quizModel_item);
//
//                }
//                POPULATING_NormalQuizModel_Subtraction();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                ShowDialog("");
//            }
//        });
//
//    }
//
//    private void POPULATING_NormalQuizModel_Subtraction()
//    {
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference(chk).child("Subtraction");
//        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (!dataSnapshot.exists()){
//                    Toast.makeText(QuizActivity.this, "Db5 is Empty", Toast.LENGTH_SHORT).show();
//                    ShowDialog("");
//                    return;
//                }
//                for (DataSnapshot dsp:dataSnapshot.getChildren())
//                {
//                    quizModel_item =   dsp.getValue(QuizModel.class);
//                    quizModel_Subtraction_Array.add(quizModel_item);
//
//                }
//                Get_Random_ImageQuestions();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                ShowDialog("");
//            }
//        });
//
//    }

    //Shuffling and collecting Quiz Qs
// new methode after topics
    private void Populating_Array() {
        Log.d("hassan", "---" + chk);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(chk).child(topic);

        Log.d("hassan", "---" + myRef);
        Log.d("hassan", "---" + topic);
        //snapshot ni mil raha
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    Toast.makeText(QuizActivity.this, "Db1 is Empty", Toast.LENGTH_SHORT).show();
                    ShowDialog("");
                    return;
                }
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    quizModel_item = dsp.getValue(QuizModel.class);
                    PopulatedArray.add(quizModel_item);

                }

                Get_Random_Questions();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(QuizActivity.this, "InterIet connection isn't stable!", Toast.LENGTH_SHORT).show();
                ShowDialog("");
            }
        });

    }

    int condition;

    private void Get_Random_Questions() {

        Log.d("hassan", "itration no :" + PopulatedArray.size());
        if (PopulatedArray.size() < 10) {
            condition = PopulatedArray.size();
        } else {
            condition = 10;
        }
        for (int i = 0; i < condition; ) {
            Log.d("hassan", "itration no :" + i + "    ," + quizModel_item.getQuestion());

            int Image_Random = rI.nextInt((PopulatedArray.size() - 1) - 0 + 1) + 0;

            /////getingQuestion Normal
            quizModel_item = PopulatedArray.get(Image_Random);
            if (!Final_TEN_Quiz.contains(quizModel_item)) {
                Final_TEN_Quiz.add(quizModel_item);
                i++;
            }

            Log.d("hassan", "itration no :" + i + "    ," + quizModel_item.getQuestion());
        }
        ///Loading First Q
        updateUI_Resulsts();
        startTimer();
        LoadQuestion();
        playmedia();
    }

//old methode
//    private void Get_Random_ImageQuestions()
//    {
////        settingup 5, Questions
//        for (int i=0; i<2;)
//        {
//
//
//            int Image_Random = rI.nextInt((pictureQuizModel_Array.size()-1 ) - 0 + 1) + 0;
//
//            /////getingQuestion Normal
//            quizModel_item=    pictureQuizModel_Array.get(Image_Random);
//            if (!Final_TEN_Quiz.contains(quizModel_item))
//            {
//                Final_TEN_Quiz.add(quizModel_item);
//                i++;
//            }
//        }
//         Get_Random_NormalQuestions_Addition();
//
//
//    }

//    private void Get_Random_NormalQuestions_Addition()
//    {
////        settingup 2 Questions
//        for (int i=0; i<2;)
//        {
//
//            int normal_Random = rI.nextInt((quizModel_Addition_Array.size()-1)  - 0 + 1) + 0;
//
//            /////getingQuestion Normal
//            quizModel_item =    quizModel_Addition_Array.get(normal_Random);
//            if (!Final_TEN_Quiz.contains(quizModel_item))
//            {
//                Final_TEN_Quiz.add(quizModel_item);
//                i++;
//            }
//        }
//        Get_Random_NormalQuestions_Subtraction();
//
//    }
//
//    private void Get_Random_NormalQuestions_Subtraction()
//    {
////        settingup 2 Questions
//        for (int i=0; i<2;)
//        {
//
//            int normal_Random = rI.nextInt((quizModel_Subtraction_Array.size()-1) - 0 + 1) + 0;
//Log.d("hassan","-----"+rI);
//            /////getingQuestion Normal
//            quizModel_item =    quizModel_Subtraction_Array.get(normal_Random);
//            if (!Final_TEN_Quiz.contains(quizModel_item))
//            {
//                Final_TEN_Quiz.add(quizModel_item);
//                i++;
//            }
//        }
//        Get_Random_NormalQuestions_Division();
//
//    }
//
//    private void Get_Random_NormalQuestions_Division()
//    {
////        settingup 2 Questions
//        for (int i=0; i<2;)
//        {
//
//            int normal_Random = rI.nextInt((quizModel_Division_Array.size()-1)  - 0 + 1) + 0;
//
//            /////getingQuestion Normal
//            quizModel_item =    quizModel_Division_Array.get(normal_Random);
//            if (!Final_TEN_Quiz.contains(quizModel_item))
//            {
//                Final_TEN_Quiz.add(quizModel_item);
//                i++;
//            }
//        }
//        Get_Random_NormalQuestions_EvenOdd();
//
//    }
//
//
//
//    private void Get_Random_NormalQuestions_EvenOdd()
//    {
////        settingup 2 Questions
//        for (int i=0; i<2;)
//        {
//
//            int normal_Random = rI.nextInt((quizModel_EvenOdd_Array.size()-1)  - 0 + 1) + 0;
//
//            /////getingQuestion Normal
//            quizModel_item =    quizModel_EvenOdd_Array.get(normal_Random);
//            if (!Final_TEN_Quiz.contains(quizModel_item))
//            {
//                Final_TEN_Quiz.add(quizModel_item);
//                i++;
//            }
//        }
//        ///Loading First Q
//        updateUI_Resulsts();
//        startTimer();
//        LoadQuestion();
//        playmedia();
//    }


    void LoadQuestion() {
        quizModel_item = Final_TEN_Quiz.get(current);
        if (TextUtils.isEmpty(quizModel_item.getQuestion().trim())) {
            correct++;
            if (current < condition - 1) {
                current++;
                updateUI_Resulsts();
                LoadQuestion();
            } else {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                updateUI_Resulsts();
            }
        }
        //loading question
        Glide.with(this)
                .load(quizModel_item.getQuestion().trim())
                .fitCenter()
//                .placeholder(R.drawable.ic_baseline_wifi_protected_setup_24)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    LoadQuestionError();

                                } catch (Exception e) {
                                    dialog.dismiss();
                                }
                            }
                        });


                        // in case of error number will be awarded
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {


                        ShowDialog("");
                        return false;
                    }
                })
                .into(Questionimg);


        if (quizModel_item.getOption_2().contains("https")) {
            // Pic answers do enable viewe2
            findViewById(R.id.PicView).setVisibility(View.VISIBLE);
            findViewById(R.id.TextView).setVisibility(View.GONE);

            Glide.with(this)
                    .load(quizModel_item.getOption_1().trim())
                    .fitCenter()
//                    .placeholder(R.drawable.ic_baseline_wifi_protected_setup_24)
                    .into(optionaPic);
            Glide
                    .with(this)
                    .load(quizModel_item.getOption_2().trim())
                    .fitCenter()
//                    .placeholder(R.drawable.ic_baseline_wifi_protected_setup_24)
                    .into(optionbPic);
        } else {

            // Text answers do enable viewe1
            findViewById(R.id.PicView).setVisibility(View.GONE);
            findViewById(R.id.TextView).setVisibility(View.VISIBLE);

            optiona.setText(quizModel_item.getOption_1());
            optionb.setText(quizModel_item.getOption_2());

        }

    }


    public void AnswerClicked(View view) {

        switch (view.getId()) {
            case R.id.optionaPic:
                if (quizModel_item.getOption_1().equals(quizModel_item.getAnswer())) {
                    correct++;

                    Log.d("hassan", " optin a pic:  correct" + correct);
                } else {
                    wrong++;

                    Log.d("hassan", " optin a pic:  wrong" + wrong);
                }
                break;
            case R.id.optionbPic:
                Log.d("hassan", " optin a pic");
                if (quizModel_item.getOption_2().equals(quizModel_item.getAnswer())) {
                    correct++;
                    Log.d("hassan", " optin a pic:  correct" + correct);
                } else {
                    wrong++;
                    Log.d("hassan", " optin a pic:  wrong" + wrong);
                }
                break;

            default:
                TextView selectedption = findViewById(view.getId());
                String selected = selectedption.getText().toString().trim();

                if (selected.equals(quizModel_item.getAnswer())) {
                    correct++;

                    Log.d("hassan", " optin a simple:  correct" + correct);
                } else {
                    wrong++;

                    Log.d("hassan", " optin a simple:  wrong" + wrong);
                }

                break;
        }

        current++;
        if (current < condition - 1) {
            ShowDialog("Loading next Question ...");

            updateUI_Resulsts();
            LoadQuestion();
        } else {
            ShowFinalResults();
        }
    }

    public void back(View view) {
        finish();
    }

    @SuppressLint("SetTextI18n")
    private void updateUI_Resulsts() {
        currenttv.setText("Attempting " + (current + 1) + "/" + condition);
        wrongtv.setText("Incorrect: " + wrong);
        correctv.setText("Correct: " + correct);
    }

    private void ShowDialog(String title) {
        if (dialog == null) {

            dialog = new ProgressDialog(this);
        }
        if (!dialog.isShowing()) {
            dialog.setTitle(title);
            dialog.setCancelable(false);
            dialog.show();
        } else {
            dialog.dismiss();
        }
    }

    private void ShowFinalResults() {
        startActivity(new Intent(this, FinalResultsActivity.class).putExtra("chk", chk).putExtra("correct", correct).putExtra("topic", topic));
        finish();

    }


    void startTimer() {
        new CountDownTimer(180000, 1000) {

            public void onTick(long millisUntilFinished) {

                String text = String.format(Locale.getDefault(), "Time Remaining: %02d : %02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);
                timer.setText(text);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {

                Toast.makeText(QuizActivity.this, "Time is up", Toast.LENGTH_SHORT).show();
                stopmedia();
                ShowFinalResults();
            }

        }.start();
    }

    @Override
    protected void onDestroy() {

        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        stopmedia();
        super.onDestroy();
    }

    void LoadQuestionError() {

        quizModel_item = Final_TEN_Quiz.get(current);
        Picasso picasso = new Picasso.Builder(getApplicationContext())
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        Glide.with(getApplicationContext())
                                .load(quizModel_item.getQuestion().trim())
                                .fitCenter()
//                .placeholder(R.drawable.ic_baseline_wifi_protected_setup_24)
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                        if (dialog.isShowing()) {
                                            dialog.dismiss();
                                            ShowDialog("There seems to be a problem with your internet connection!\n Please wait or quit the Quiz if taking too long");
                                        }
                                        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();

                                        correct++;
                                        if (current < condition - 1) {
                                            if (dialog.isShowing()) {
                                                dialog.dismiss();
                                            }
                                            current++;
                                            updateUI_Resulsts();
                                            LoadQuestion();
                                        } else {
                                            if (dialog.isShowing()) {
                                                dialog.dismiss();
                                            }
                                            updateUI_Resulsts();
                                        }

                                        // in case of error number will be awarded
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {


                                        ShowDialog("");
                                        return false;
                                    }
                                })
                                .into(Questionimg);
                    }
                })
                .build();


        picasso.load(quizModel_item.getQuestion().trim())
                .fit()
                .into(Questionimg);
        //loading question


        if (quizModel_item.getOption_2().contains("https")) {
            // Pic answers do enable viewe2
            findViewById(R.id.PicView).setVisibility(View.VISIBLE);
            findViewById(R.id.TextView).setVisibility(View.GONE);

            Glide.with(this)
                    .load(quizModel_item.getOption_1().trim())
                    .fitCenter()
//                    .placeholder(R.drawable.ic_baseline_wifi_protected_setup_24)
                    .into(optionaPic);
            Glide
                    .with(this)
                    .load(quizModel_item.getOption_2().trim())
                    .fitCenter()
//                    .placeholder(R.drawable.ic_baseline_wifi_protected_setup_24)
                    .into(optionbPic);
        } else {

            // Text answers do enable viewe1
            findViewById(R.id.PicView).setVisibility(View.GONE);
            findViewById(R.id.TextView).setVisibility(View.VISIBLE);

            optiona.setText(quizModel_item.getOption_1());
            optionb.setText(quizModel_item.getOption_2());

        }

    }

    MediaPlayer mp;

    private void playmedia() {
        mp = MediaPlayer.create(this, R.raw.sound);
        mp.start();


    }

    private void stopmedia() {
        try {
            if (mp != null) {
                if (mp.isPlaying()) {
                    mp.stop();
                    mp.release();
                } else {

                    mp.release();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}