package com.mathcity.myapplication.Utills;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.mathcity.myapplication.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
public class NavigationDrawer implements View.OnClickListener {

    //State is the visible state
    private Boolean state = false, Transition = false;
    private RelativeLayout mainView;
    private RelativeLayout navigationLayout;
    private Animation show, hide;

    public NavigationDrawer(Activity activity, RelativeLayout view) {

        this.mainView = view;

        LayoutInflater layoutInflater = (LayoutInflater)
                activity.getSystemService(LAYOUT_INFLATER_SERVICE);
        navigationLayout = (RelativeLayout) layoutInflater.inflate(R.layout.activity_quiz,
                mainView, false);
//navigationLayout.findViewById(R.id.back).setOnClickListener(this::onClick);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        //Show animation
        show = new TranslateAnimation(-displayMetrics.widthPixels, 0, 0, 0);
        show.setDuration(500);
        show.getFillAfter();
        show.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Transition = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Transition = false;
                state = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //Hide animation
        hide = new TranslateAnimation(0, -displayMetrics.widthPixels, 0, 0);
        hide.setDuration(500);
        hide.getFillAfter();

        hide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Transition = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Transition = false;
                state = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void toggle() {
        if (!Transition) {
            if (!state) {
                mainView.addView(navigationLayout);
                navigationLayout.startAnimation(show);
            } else {
                navigationLayout.startAnimation(hide);
                mainView.removeView(navigationLayout);
            }
        }
    }
    public void closetoogle(){
        if (state) {

            navigationLayout.startAnimation(hide);
            mainView.removeView(navigationLayout);
        }
    }
    @Override
    public void onClick(View v) {
        toggle();

    }
}