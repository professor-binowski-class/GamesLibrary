package com.example.mockuppage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView resultsTextView = findViewById(R.id.resultsTextView);
        resultsTextView.setText(getIntent().getStringExtra("result"));
    }


     //called when back button is pressed

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
