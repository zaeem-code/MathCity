package com.mathcity.myapplication.Dialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.mathcity.myapplication.Activities.QuizActivity;
import com.mathcity.myapplication.Activities.TopicActivity;
import com.mathcity.myapplication.R;

public class Dialog_testing_Fragment extends DialogFragment {


    String chk="Easy",topic="Addition";
    View v;
    TopicActivity activity;
    public Dialog_testing_Fragment(String chk, String topic, TopicActivity topicActivity) {
        this.chk = chk;
        this.topic=topic;
        activity=topicActivity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return super.onCreateDialog(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         v=inflater.inflate(R.layout.dialog_testing_section, container, false);

        return v;
    }
    private  void init(){
        v.findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().startActivity(new Intent(getActivity(), QuizActivity.class).putExtra("chk",chk).putExtra("topic",topic));
                dismiss();
                activity.finish();
            }
        });

        v.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
dismiss();
            }
        });


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);






        init();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

}
