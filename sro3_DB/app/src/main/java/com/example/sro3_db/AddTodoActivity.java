// Указывает пакет, к которому принадлежит данный класс
package com.example.sro3_db;

// Импорт стандартных классов Android для работы с UI и функционалом
import android.app.DatePickerDialog; // Диалоговое окно выбора даты
import android.content.ContentValues; // Класс для формирования пар "ключ-значение" для БД
import android.database.sqlite.SQLiteDatabase; // Класс для управления БД SQLite
import android.os.Bundle; // Класс для передачи данных между состояниями Activity
import android.widget.EditText; // Виджет текстового поля ввода
import android.widget.Toast; // Всплывающие уведомления

// Импорт базового класса Activity для поддержки новых функций на старых версиях Android
import androidx.appcompat.app.AppCompatActivity;

// Импорт Java-класса для работы с датой и временем
import java.util.Calendar;

// Объявление класса Activity, который наследуется от AppCompatActivity
public class AddTodoActivity extends AppCompatActivity {
    // Объявление переменных для полей ввода (EditText)
    EditText etTitle, etDeadline, etCategory;
    // Объявление объекта помощника для работы с БД
    DBHelper dbHelper;

    // Метод, вызываемый при создании Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Установка макета (дизайна) из XML-файла
        setContentView(R.layout.activity_add_todo);

        // Инициализация помощника БД, передаем текущий контекст (this)
        dbHelper = new DBHelper(this);

        // Связывание переменных с элементами интерфейса по их ID из XML
        etTitle = findViewById(R.id.etTitle);
        etDeadline = findViewById(R.id.etDeadline);
        etCategory = findViewById(R.id.etCategory);

        // Установка слушателя нажатия на поле дедлайна для вызова календаря
        etDeadline.setOnClickListener(v -> showDateTimePicker());

        // Установка слушателя на кнопку добавления для вызова метода сохранения
        findViewById(R.id.btnAdd).setOnClickListener(v -> saveTodo());
    }

    // Метод для отображения диалога выбора даты
    private void showDateTimePicker() {
        // Получаем экземпляр календаря с текущей датой
        Calendar cal = Calendar.getInstance();
        // Создаем и показываем DatePickerDialog
        new DatePickerDialog(this, (view, year, month, day) -> {
            // Формируем строку даты (месяц +1, так как в Java месяцы начинаются с 0)
            String date = day + "." + (month + 1) + "." + year;
            // Устанавливаем выбранную дату в текстовое поле
            etDeadline.setText(date);
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
    }

    // Метод для сохранения данных в базу данных
    private void saveTodo() {
        // Получаем текст из полей ввода и преобразуем в строки
        String title = etTitle.getText().toString();
        String deadline = etDeadline.getText().toString();
        String category = etCategory.getText().toString();

        // Простая проверка: если заголовок пуст, прекращаем выполнение метода
        if (title.isEmpty()) return;

        // Открываем базу данных для записи
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // Создаем объект ContentValues для хранения данных задачи
        ContentValues cv = new ContentValues();
        // Добавляем данные в объект (название колонки -> значение)
        cv.put(DBHelper.COL_TITLE, title);
        cv.put(DBHelper.COL_DEADLINE, deadline);
        cv.put(DBHelper.COL_CATEGORY, category);

        // Вставляем строку в таблицу и получаем ID новой записи (или -1 при ошибке)
        long result = db.insert(DBHelper.TABLE_NAME, null, cv);

        // Проверяем результат вставки
        if (result != -1) {
            // Если успех — выводим радостное сообщение
            Toast.makeText(this, "Задача добавлена!", Toast.LENGTH_SHORT).show();
        } else {
            // Если ошибка (-1) — уведомляем пользователя
            Toast.makeText(this, "Ошибка при добавлении", Toast.LENGTH_SHORT).show();
        }

        // Закрываем текущую Activity и возвращаемся на предыдущий экран (обычно список дел)
        finish();
    }
}