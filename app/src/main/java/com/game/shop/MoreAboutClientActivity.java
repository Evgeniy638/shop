package com.game.shop;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class MoreAboutClientActivity extends Activity {
    String data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = getIntent().getStringExtra("data");


    }
}
