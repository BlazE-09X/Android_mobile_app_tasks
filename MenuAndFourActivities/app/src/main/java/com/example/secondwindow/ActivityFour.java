package com.example.secondwindow;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityFour extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        Button btn = findViewById(R.id.button_to_one);
        btn.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Этот метод указывает системе использовать наш XML-файл меню
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Здесь мы обрабатываем клики по пунктам меню
        int id = item.getItemId();

        if (id == R.id.action_window1) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        } else if (id == R.id.action_window2) {
            // Проверь, что класс называется именно ActivityTwo
            startActivity(new Intent(this, ActivityTwo.class));
            return true;
        } else if (id == R.id.action_window3) {
            startActivity(new Intent(this, ActivityThree.class));
            return true;
        } else if (id == R.id.action_window4) {
            startActivity(new Intent(this, ActivityFour.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}