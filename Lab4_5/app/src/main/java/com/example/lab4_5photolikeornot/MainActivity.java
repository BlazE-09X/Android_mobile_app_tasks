package com.example.lab4_5photolikeornot;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Имена ваших файлов в папке drawable
    private int[] images = {R.drawable.image_1, R.drawable.image_2};
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageView = findViewById(R.id.imageViewMain);
        Button btnLike = findViewById(R.id.buttonLike);
        Button btnDislike = findViewById(R.id.buttonDislike);
        Button btnSearch = findViewById(R.id.button_search);


        imageView.setImageResource(images[currentIndex]);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % images.length;
                imageView.setImageResource(images[currentIndex]);
            }
        };

        btnLike.setOnClickListener(listener);
        btnDislike.setOnClickListener(listener);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = 0;
                imageView.setImageResource(images[currentIndex]);
            }
        });
    }
}