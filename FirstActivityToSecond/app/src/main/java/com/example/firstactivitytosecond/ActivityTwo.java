package com.example.firstactivitytosecond;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityTwo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        TextView tvName = findViewById(R.id.textResultName);

        // Получаем данные из интента по ключу
        String name = getIntent().getStringExtra("USER_NAME_KEY");

        if (name != null && !name.isEmpty()) {
            tvName.setText("Ваш текст, " + name);
        }
    }
}