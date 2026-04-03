package com.example.firstactivitytosecond;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.firstactivitytosecond.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editName = findViewById(R.id.editUserName);
        Button btn = findViewById(R.id.button_to_two);

        btn.setOnClickListener(v -> {
            String name = editName.getText().toString();

            Intent intent = new Intent(this, ActivityTwo.class);
            intent.putExtra("USER_NAME_KEY", name);
            startActivity(intent);
        });
    }
}