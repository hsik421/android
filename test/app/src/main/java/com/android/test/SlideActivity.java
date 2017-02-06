package com.android.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.test.custom.AutoSlideLayout;

public class SlideActivity extends AppCompatActivity {

    AutoSlideLayout slideLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_activity);
        slideLayout = (AutoSlideLayout)findViewById(R.id.slide_layout);
    }
}
