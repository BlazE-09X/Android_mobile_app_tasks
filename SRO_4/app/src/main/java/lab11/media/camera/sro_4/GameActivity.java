package lab11.media.camera.sro_4;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast; // Добавили тосты

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    Button[] buttons = new Button[9];
    boolean xTurn = true;
    boolean isGameOver = false; // Флаг, чтобы нельзя было ходить после победы
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        pref = getSharedPreferences("StudentPrefs", MODE_PRIVATE);

        GridLayout grid = findViewById(R.id.gameGrid);
        // Цикл для создания кнопок прямо в коде (чтобы не писать 9 штук в XML)
        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button(this);
            buttons[i].setTextSize(24);

            // Запоминаем индекс кнопки для обработчика клика
            int finalI = i;
            buttons[i].setOnClickListener(v -> onCellClick(finalI));

            // Добавляем кнопку в сетку и задаем ей размер 200x200 пикселей
            grid.addView(buttons[i], 200, 200);
        }

        // Кнопка очистки (Reset) уже было в XML
        findViewById(R.id.btnReset).setOnClickListener(v -> resetGame());

        // Загружаем состояние поля (если мы выходили из приложения)
        loadGameState();
    }

    private void onCellClick(int i) {
        // Если игра окончена или клетка уже занята — ничего не делаем
        if (isGameOver || !buttons[i].getText().toString().equals("")) return;

        // Ставим X или O в зависимости от очереди
        buttons[i].setText(xTurn ? "X" : "O");

        // Проверяем, привел ли этот ход к победе
        if (checkWinner()) {
            String winner = xTurn ? "Крестики выиграли!" : "Нолики выиграли!";
            Toast.makeText(this, winner, Toast.LENGTH_LONG).show();
            isGameOver = true;
        }
        // Если победителя нет, проверяем, не закончились ли пустые клетки
        else if (isFieldFull()) { // Проверка на ничью
            Toast.makeText(this, "Ничья! Очистите поле.", Toast.LENGTH_LONG).show();
            isGameOver = true;
        }
        //Если игра продолжается — передаем ход другому
        else {
            xTurn = !xTurn;
        }
        // Сохраняем каждый ход, чтобы прогресс не потерялся
        saveGameState();
    }

    private boolean isFieldFull() {
        for (Button b : buttons) {
            if (b.getText().toString().equals("")) return false;
        }
        return true;
    }

    private boolean checkWinner() {
        // Выигрышные комбинации (индексы кнопок)
        int[][] winPositions = {
                {0,1,2}, {3,4,5}, {6,7,8}, // Горизонтали
                {0,3,6}, {1,4,7}, {2,5,8}, // Вертикали
                {0,4,8}, {2,4,6}           // Диагонали
        };

        for (int[] pos : winPositions) {
            String b1 = buttons[pos[0]].getText().toString();
            String b2 = buttons[pos[1]].getText().toString();
            String b3 = buttons[pos[2]].getText().toString();

            // Если все три клетки в линии одинаковые и не пустые — есть победитель!
            if (!b1.equals("") && b1.equals(b2) && b2.equals(b3)) {
                return true;
            }
        }
        return false;
    }

    private void saveGameState() {
        StringBuilder sb = new StringBuilder();
        for (Button b : buttons) {
            String txt = b.getText().toString();
            // Кодируем: пустая = 0, X = 1, O = 2. Получится строка типа "102100210"
            sb.append(txt.equals("") ? "0" : (txt.equals("X") ? "1" : "2"));
        }
        // Сохраняем состояние поля, чей ход и закончена ли игра
        pref.edit()
                .putString("game_state", sb.toString())
                .putBoolean("x_turn", xTurn)
                .putBoolean("game_over", isGameOver)
                .apply();
    }

    private void loadGameState() {
        String state = pref.getString("game_state", "000000000");
        xTurn = pref.getBoolean("x_turn", true);
        isGameOver = pref.getBoolean("game_over", false);

        for (int i = 0; i < 9; i++) {
            char c = state.charAt(i);
            // Раскодируем символы обратно в текст на кнопках
            buttons[i].setText(c == '1' ? "X" : (c == '2' ? "O" : ""));
        }
    }

    private void resetGame() {
        for (Button b : buttons) {
            b.setText("");
        }
        xTurn = true;
        isGameOver = false;
        saveGameState(); // Сбрасываем и в памяти тоже
        Toast.makeText(this, "Поле очищено", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveGameState(); // Гарантированное сохранение при сворачивании
    }
}