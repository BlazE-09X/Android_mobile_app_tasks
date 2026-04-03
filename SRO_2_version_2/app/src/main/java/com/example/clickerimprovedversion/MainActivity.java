package com.example.clickerimprovedversion;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;
import java.util.Random;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;



public class MainActivity extends AppCompatActivity {
    private int score = 0;
    ImageView imageClicker;
    Button buttonMinus;
    Button buttonReset;
    TextView textCounter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textCounter = findViewById(R.id.TextCounter);
        imageClicker = findViewById(R.id.ImageClicker);
        buttonMinus = findViewById(R.id.ButtonMinus);
        buttonReset = findViewById(R.id.ButtonReset);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayShowTitleEnabled(false);


        imageClicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score++;
                updateUI();
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (score > 0) {
                    score--;
                }
                updateUI();
            }
        });


        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = 0;
                updateUI();
            }
        });
    }


    private void updateUI() {

        String suffix;
        int lastDigit = score % 10;
        int lastTwoDigits = score % 100;

        // В русском языке: 2, 3, 4 раза. Остальное (1, 5, 10, 21...) - раз.
        // Исключение: 12, 13, 14 - это тоже "раз"
        if (lastDigit >= 2 && lastDigit <= 4 && !(lastTwoDigits >= 12 && lastTwoDigits <= 14)) {
            suffix = " раза";
        } else {
            suffix = " раз";
        }

        textCounter.setText("Кнопка нажата " + score + suffix);
    }
}