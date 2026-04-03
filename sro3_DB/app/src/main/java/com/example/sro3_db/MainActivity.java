// Указывает пакет проекта
package com.example.sro3_db;

// Импорт необходимых классов Android
import android.content.Intent; // Для перехода между экранами
import android.database.Cursor; // Для хранения результата запроса из БД
import android.database.sqlite.SQLiteDatabase; // Для работы с БД
import android.os.Bundle; // Для работы с состоянием Activity
import androidx.appcompat.app.AppCompatActivity; // Базовый класс для Activity
import androidx.recyclerview.widget.LinearLayoutManager; // Управляет расположением элементов в списке (вертикально/горизонтально)
import androidx.recyclerview.widget.RecyclerView; // Современный виджет для отображения больших списков

public class MainActivity extends AppCompatActivity {
    // Объявление переменных
    DBHelper dbHelper;   // Помощник для работы с БД
    TodoAdapter adapter; // Адаптер, который связывает данные из БД с визуальным списком
    RecyclerView recyclerView; // Сам виджет списка на экране

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Установка главного макета (где находится RecyclerView и кнопка FAB)
        setContentView(R.layout.activity_main);

        // Инициализация помощника БД
        dbHelper = new DBHelper(this);

        // Поиск RecyclerView в макете и его настройка
        recyclerView = findViewById(R.id.recyclerView);
        // Указываем, что список должен быть линейным (как обычный вертикальный список)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Находим плавающую кнопку (Floating Action Button) для добавления задачи
        findViewById(R.id.fabAdd).setOnClickListener(v -> {
            // При нажатии создаем Intent для перехода в AddTodoActivity
            startActivity(new Intent(this, AddTodoActivity.class));
        });
    }

    // Метод жизненного цикла, который вызывается каждый раз, когда экран становится видимым
    @Override
    protected void onResume() {
        super.onResume();
        // Обновляем список данных при возвращении на экран (например, после добавления задачи)
        updateList();
    }

    // Метод для получения данных из БД и обновления интерфейса
    private void updateList() {
        // Открываем базу данных только для чтения (getReadableDatabase)
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Делаем запрос SELECT * к таблице. Результат возвращается в виде Cursor (указатель на данные)
        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);

        // Проверяем, создан ли уже адаптер
        if (adapter == null) {
            // Если нет — создаем новый адаптер, передаем в него данные (cursor)
            adapter = new TodoAdapter(cursor);
            // Привязываем адаптер к RecyclerView
            recyclerView.setAdapter(adapter);
        } else {
            // Если адаптер уже есть, просто заменяем старые данные (cursor) на новые
            adapter.swapCursor(cursor);
        }
    }
}