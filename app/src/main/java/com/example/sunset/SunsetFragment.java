package com.example.sunset;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SunsetFragment extends Fragment {

    private View mSceneView;
    private View mSunView;
    private View mSkyView;

    private int mBlueSkyColor;
    private int mSunsetSkyColor;
    private int mNightSkyColor;

    AnimatorSet animatorSet = new AnimatorSet();
    boolean downwards;

    public static SunsetFragment newInstance(){
        return new SunsetFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sunset,container,false);

        mSceneView=view;
        mSunView=view.findViewById(R.id.sun);
        mSkyView=view.findViewById(R.id.sky);

        Resources resources=getResources();
        mBlueSkyColor=resources.getColor(R.color.blue_sky);
        mSunsetSkyColor=resources.getColor(R.color.sunset_sky);
        mNightSkyColor=resources.getColor(R.color.night_sky);

        mSceneView.setOnClickListener((v)->startAnimation());

        return view;
    }

    private void startAnimation(){

        float sunYStart=mSunView.getTop();
        float sunYEnd=mSkyView.getHeight();
//        AnimatorSet animatorSet = new AnimatorSet();

        if (!animatorSet.isStarted()) {

            if (!downwards) {
                ObjectAnimator heightAnimator = ObjectAnimator.ofFloat(mSunView, "y", sunYStart, sunYEnd).setDuration(3000);
                heightAnimator.setInterpolator(new AccelerateInterpolator());

                ObjectAnimator sunsetSkyAnimator = ObjectAnimator.ofInt(mSkyView, "backgroundColor", mBlueSkyColor, mSunsetSkyColor).setDuration(3000);
                sunsetSkyAnimator.setEvaluator(new ArgbEvaluator());

                ObjectAnimator nightSkyAnimator = ObjectAnimator.ofInt(mSkyView, "backgroundColor", mSunsetSkyColor, mNightSkyColor).setDuration(3000);
                nightSkyAnimator.setEvaluator(new ArgbEvaluator());

                animatorSet.play(heightAnimator).with(sunsetSkyAnimator).before(nightSkyAnimator);
                animatorSet.start();

            } else {

                ObjectAnimator heightAnimator = ObjectAnimator.ofFloat(mSunView, "y", sunYEnd, sunYStart).setDuration(3000);
                heightAnimator.setInterpolator(new AccelerateInterpolator());

                ObjectAnimator sunsetSkyAnimator = ObjectAnimator.ofInt(mSkyView, "backgroundColor", mSunsetSkyColor, mBlueSkyColor).setDuration(3000);
                sunsetSkyAnimator.setEvaluator(new ArgbEvaluator());

                ObjectAnimator nightSkyAnimator = ObjectAnimator.ofInt(mSkyView, "backgroundColor", mNightSkyColor, mSunsetSkyColor).setDuration(3000);
                nightSkyAnimator.setEvaluator(new ArgbEvaluator());

//                ObjectAnimator pulseAnimator = ObjectAnimator.ofPropertyValuesHolder(mSunView, PropertyValuesHolder.ofFloat("scaleX", 1.2f),
//                        PropertyValuesHolder.ofFloat("scaleY", 1.2f));
//                pulseAnimator.setDuration(310);
//                pulseAnimator.setRepeatCount(ObjectAnimator.INFINITE);
//                pulseAnimator.setRepeatMode(ObjectAnimator.REVERSE);

                animatorSet.play(heightAnimator).before(sunsetSkyAnimator);
                animatorSet.start();
            }

            downwards = !downwards;
        }
    }
}
