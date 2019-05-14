package com.example.mockuppage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class GameActivityExample extends AppCompatActivity {

    private static final String TAG = "GameActivityExample";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_example);
        Log.d(TAG, "onCreate: called.");
    }
}
