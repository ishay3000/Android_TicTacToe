package com.example.ishaycena.tic_tac_toe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();

        int imgResourcePath = intent.getExtras().getInt("GAME_RESULT", 0);
        if (imgResourcePath != 0){
            LinearLayout layout = findViewById(R.id.lnMain);
            ImageView view = new ImageView(ResultsActivity.this);
            view.setBackgroundResource(imgResourcePath);
            layout.addView(view);
        }
    }
}
