package com.example.a6_7lablotwindow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;

public class MainActivity extends Activity {
    private float x1, x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn_to_philippines);
        btn.setOnClickListener(v -> openPhilippines());
    }

    // Вынесли переход в отдельный метод, чтобы вызывать его и по кнопке, и по свайпу
    private void openPhilippines() {
        Intent intent = new Intent(MainActivity.this, activity_philippines.class);
        startActivity(intent);

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    // Метод для отслеживания жестов (слайдинг)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (deltaX > 150) {
                    openPhilippines();
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}