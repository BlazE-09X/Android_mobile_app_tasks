package com.example.a6_7lablotwindow;

import android.app.Activity; // Используем стандартный Activity
import android.app.AlertDialog; // Важно: стандартный AlertDialog
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class activity_philippines extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_philippines);

        // Находим корневой layout по ID, чтобы менять ему фон
        RelativeLayout layout = findViewById(R.id.layout_philippines);

        findViewById(R.id.btn_dialog).setOnClickListener(v -> {
            String[] colors = {"Красный", "Зеленый", "Синий"};
            // Используем стандартные цвета Android или HEX
            int[] colorValues = {0xFFFF0000, 0xFF00FF00, 0xFF0000FF};

            // Создаем диалог через стандартный Builder
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Выберите цвет");
            builder.setItems(colors, (dialog, which) -> {
                layout.setBackgroundColor(colorValues[which]);
                Toast.makeText(this, "Выбран: " + colors[which], Toast.LENGTH_SHORT).show();
            });
            builder.show();
        });
    }
}