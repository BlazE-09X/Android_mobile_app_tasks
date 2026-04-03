package com.example.secondwindow;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.secondwindow.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button_to_two);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityTwo.class);
            startActivity(intent);
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