// Указывает пакет проекта
package com.example.sro3_db;

// Импорт необходимых классов Android для работы с контекстом и SQLite
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper; // Базовый класс для управления БД

// Объявление класса, наследующегося от SQLiteOpenHelper
public class DBHelper extends SQLiteOpenHelper {

    // Константы для названия таблицы и имен столбцов (чтобы не ошибиться в запросах)
    public static final String TABLE_NAME = "todos"; // Имя таблицы
    public static final String COL_ID = "_id";      // Уникальный ID (первичный ключ)
    public static final String COL_TITLE = "title"; // Название задачи
    public static final String COL_DEADLINE = "deadline"; // Дата выполнения
    public static final String COL_CATEGORY = "category"; // Категория (работа, учеба и т.д.)

    // Конструктор класса
    public DBHelper(Context context) {
        // super вызывает конструктор родителя:
        // context — контекст приложения
        // "TodoDB" — имя файла базы данных на устройстве
        // null — стандартный CursorFactory
        // 1 — версия базы данных (при изменении структуры ее нужно будет поднять)
        super(context, "TodoDB", null, 1);
    }

    // Метод вызывается ОДИН РАЗ, когда база данных создается впервые
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Выполнение SQL-запроса на создание таблицы
        // Используются константы, чтобы структура соответствовала объявленным именам
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // ID с автоинкрементом
                COL_TITLE + " TEXT, " +                          // Текстовое поле для заголовка
                COL_DEADLINE + " TEXT, " +                       // Текстовое поле для даты
                COL_CATEGORY + " TEXT);");                       // Текстовое поле для категории
    }

    // Метод вызывается при обновлении версии БД (например, с 1 на 2)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Здесь обычно пишут логику удаления старой таблицы и создания новой
        // Сейчас метод пуст, так как версия БД начальная
    }
}