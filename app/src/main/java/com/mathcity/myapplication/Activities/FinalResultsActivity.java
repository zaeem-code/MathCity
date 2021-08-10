package com.mathcity.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mathcity.myapplication.R;
import com.mathcity.myapplication.Utills.Common;
import com.mathcity.myapplication.model.parents;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class FinalResultsActivity extends AppCompatActivity {
    ImageView reaction;
    RelativeLayout headermain;
    TextView next, msgPF, msgdet, score, btnTXT;

    String chk = "Easy", topic = "Addition";
    int correct = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_final_results);
        headermain = findViewById(R.id.header);
        msgPF = findViewById(R.id.msgPF);
        msgdet = findViewById(R.id.msgdet);
        score = findViewById(R.id.scroe);
        next = findViewById(R.id.next);
        btnTXT = findViewById(R.id.btnTXT);
        reaction = findViewById(R.id.reaction);

        chk = getIntent().getStringExtra("chk");
        topic = getIntent().getStringExtra("topic");
        correct = getIntent().getIntExtra("correct", 0);


        switch (chk) {

            case "Medium":

                headermain.setBackgroundColor(Color.parseColor("#ffa500"));

                msgPF.setTextColor(Color.parseColor("#ffa500"));
                score.setTextColor(Color.parseColor("#ffa500"));
                next.setBackgroundResource(R.drawable.cm);
                if (correct > 6) {
//pass
                    reaction.setBackgroundResource(R.drawable.passm);
                } else {

                    reaction.setBackgroundResource(R.drawable.failm);
                }


                break;

            case "Hard":
                headermain.setBackgroundColor(Color.RED);

                msgPF.setTextColor(Color.RED);
                score.setTextColor(Color.RED);
                next.setBackgroundResource(R.drawable.ch);

                if (correct > 6) {
//pass
                    reaction.setBackgroundResource(R.drawable.passh);
                } else {
                    reaction.setBackgroundResource(R.drawable.failh);
                }
                break;

            default:

                if (correct > 6) {
//pass
                    reaction.setBackgroundResource(R.drawable.passe);
                } else {

                    reaction.setBackgroundResource(R.drawable.faile);
                }
                break;

        }

        WritingDB();


    }

    void WritingDB() {

        score.setText(correct + "/10");


        Common common = new Common(this);
        common.SetTotalScore(common.GetTotalScore() + correct);


        if (correct == 10) {
            common.SetBonusScore(common.GetBonusScore() + 3);
        } else if (correct == 9) {

            common.SetBonusScore(common.GetBonusScore() + 2);
        } else if (correct == 8) {

            common.SetBonusScore(common.GetBonusScore() + 1);
        }
        if (correct > 6) {
            //pass above 6
            findViewById(R.id.next).setVisibility(View.VISIBLE);
            findViewById(R.id.viewfail).setVisibility(View.GONE);
            msgdet.setText("You pass the test\nYour total score is");
            msgPF.setText("PASS");
            common.SetTotalAttemptsPASS(common.GetTotalAttemptsPASS() + 1);
            common.SetTotalAttemptsPASSSUBJECT(common.GetTotalAttemptsPASSSUBJECT(topic) + 1, topic);
        } else {


            findViewById(R.id.viewfail).setVisibility(View.VISIBLE);
            findViewById(R.id.next).setVisibility(View.GONE);
            msgPF.setText("FAIL");
            common.SetTotalAttemptsFAILL(common.GetTotalAttemptsFAILL() + 1);
            common.SetTotalAttemptsFAILLSUBJECT(common.GetTotalAttemptsFAILLSUBJECT(topic) + 1, topic);


            switch (chk) {

                case "Medium":
                    msgdet.setText("Don't worry try difficulty level: EASY");
                    btnTXT.setText("go to Easy mode");
                    intent = new Intent(this, TopicActivity.class).putExtra("chk", "Easy");


                    break;

                case "Hard":

                    msgdet.setText("Don't worry try difficulty level: MEDIUM");

                    btnTXT.setText("go to Medium mode");
                    intent = new Intent(this, TopicActivity.class).putExtra("chk", "Medium");

                    break;

                default:

                    msgdet.setText("Don't worry join our learning section and try again");
                    intent = new Intent(this, Learning_List_Activity.class);

                    break;

            }


        }


        common.SetTotalAttempts(common.GetTotalAttempts() + 1);
        common.SetTotalAttemptsSUBJECT(common.GetTotalAttemptsSUBJECT(topic) + 1, topic);


        int obtain = common.GetTotalAttemptsPASSSUBJECT(topic);
        int ttl = common.GetTotalAttemptsSUBJECT(topic);
        float percentage = (obtain / ttl) * 100;
        if (percentage < 70f) {
            //week area
            if (!common.GetWeakAreas().contains(topic)) {
                if (TextUtils.isEmpty(common.GetWeakAreas())) {
                    common.SetWeakAreas(topic);
                } else {
                    common.SetWeakAreas(" ," + topic);
                }
            } else {
                if (TextUtils.isEmpty(common.GetWeakAreas())) {
                    common.SetWeakAreas(topic);
                } else {
                    common.SetWeakAreas(" ," + topic);
                }
            }


// strong area
            if (!common.GetStrongAreas().contains(topic)) {
                if (TextUtils.isEmpty(common.GetStrongAreas())) {
                    common.SetStrongAreas(topic);
                } else {
                    common.SetStrongAreas(" ," + topic);
                }
            } else {
                if (TextUtils.isEmpty(common.GetStrongAreas())) {
                    common.SetStrongAreas(topic);
                } else {
                    common.SetStrongAreas(" ," + topic);
                }
            }


        } else {
            //week area
            if (common.GetWeakAreas().contains(" ," + topic)) {
                common.SetWeakAreas(common.GetWeakAreas().replace(" ," + topic, ""));
            } else {
                common.SetWeakAreas(common.GetWeakAreas().replace(topic, ""));
            }

//strong area
            if (common.GetStrongAreas().contains(" ," + topic)) {
                common.SetStrongAreas(common.GetStrongAreas().replace(" ," + topic, ""));
            } else {
                common.SetStrongAreas(common.GetStrongAreas().replace(topic, ""));
            }
        }

        if (checkIfAlreadyhavePermission()) {
            takeScreenshot();
        }
    }


    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public void back(View view) {
        finish();
    }

    Intent intent;

    public void gotovids(View view) {

        startActivity(intent);
        finish();
    }

    private void takeScreenshot() {
        Toast.makeText(this, "taking", Toast.LENGTH_SHORT).show();
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            openScreenshot(imageFile);
        } catch (Throwable e) {
            SendEMAIL(null);
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile) {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
//        intent.setDataAndType(uri, "image/*");
//        startActivity(intent);
        SendEMAIL(uri);
    }

    private void SendEMAIL(Uri imageFile) {
        Toast.makeText(this, "Report ready", Toast.LENGTH_SHORT).show();
        parents item = new Common(this).Getparentsdetais();
        if (item != null) {
            if (!item.getEmail().isEmpty()) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{item.getEmail()});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "MathCity Quiz Results");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Quiz mode was " + chk + "\n Final Result is: " + msgPF.getText().toString() + "\n Total obtained " + score.getText().toString().trim());
//        File root = Environment.getExternalStorageDirectory();
//        String pathToMyAttachedFile = "temp/attachement.xml";
//        File file = new File(imageFile, pathToMyAttachedFile);
//        if (!im.exists() || !file.canRead()) {
//            return;
//        }
//        Uri uri = Uri.fromFile(file);
                emailIntent.putExtra(Intent.EXTRA_STREAM, imageFile);
                startActivity(Intent.createChooser(emailIntent, "Pick an Email provider"));
            }
        }
    }
}