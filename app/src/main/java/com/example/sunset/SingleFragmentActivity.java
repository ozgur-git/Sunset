package com.example.sunset;

import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.logging.Logger;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    Logger mLogger=Logger.getLogger(getClass().getName());

    @LayoutRes
    private int getLayoutResId(){

        return R.layout.activity_fragment;
    }

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(getLayoutResId());

        if  (savedInstanceState==null) {

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, createFragment()).commit();
        }
    }
}
