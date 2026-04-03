package com.example.threebuttons;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.threebuttons.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    EditText etInput;
    Button bControl;
    int guess;
    boolean gameFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvInfo = (TextView) findViewById(R.id.textView1);
        etInput = (EditText) findViewById(R.id.editText1);
        bControl = (Button) findViewById(R.id.button1);


        guess = (int)(Math.random() * 6);
        gameFinished = false;
    }


    public void onClick(View v) {
        if (!gameFinished) {
            // Проверка на пустой ввод, чтобы приложение не вылетало
            String inputStr = etInput.getText().toString();
            if (inputStr.isEmpty()) return;

            int inp = Integer.parseInt(inputStr);

            if (inp > guess) {
                tvInfo.setText(getResources().getString(R.string.ahead));
            } else if (inp < guess) {
                tvInfo.setText(getResources().getString(R.string.behind));
            } else {
                tvInfo.setText(getResources().getString(R.string.hit));
                bControl.setText(getResources().getString(R.string.play_more));
                gameFinished = true;
            }
        } else {

            guess = (int)(Math.random() * 6);
            bControl.setText(getResources().getString(R.string.input_value));
            tvInfo.setText(getResources().getString(R.string.try_to_guess));
            gameFinished = false;
        }

        etInput.setText("");
    }
}