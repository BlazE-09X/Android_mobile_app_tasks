package com.example.kliker;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;
import java.util.Random;

import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;



public class MainActivity extends AppCompatActivity {
    private int score = 0;
    private Random random = new Random();
    TextView TextCounter;
    Button ButtonClick;

    LinearLayout mineButtonLayoutb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mineButtonLayoutb = findViewById(R.id.mineButtonLayout);
        TextCounter = findViewById(R.id.TextCounter);
        ButtonClick = findViewById(R.id.ButtonClick);

        Toolbar mytoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mytoolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        ButtonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score++;
                TextCounter.setText("Кнопка нажата: " + score);

                int r = random.nextInt(256);
                int g = random.nextInt(256);
                int b = random.nextInt(256);
                int randomColor = Color.rgb(r, g, b);


                ButtonClick.setBackgroundTintList(ColorStateList.valueOf(randomColor));

                double fortext = (0.299 * r + 0.587 * g + 0.144 * b) / 255;

                if (fortext < 0.5) {
                    ButtonClick.setTextColor(Color.WHITE);
                }
                else {
                    ButtonClick.setTextColor(Color.BLACK);
                }
            }
        });
    }
}