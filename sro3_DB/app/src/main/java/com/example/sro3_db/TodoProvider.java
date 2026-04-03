// Указывает пакет проекта
package com.example.sro3_db;

// Импорт стандартных классов для работы с провайдером контента и данными
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

// Класс-провайдер, расширяющий стандартный ContentProvider
public class TodoProvider extends ContentProvider {
    // Ссылка на наш вспомогательный класс базы данных
    private DBHelper dbHelper;

    // AUTHORITY — это уникальная строка-идентификатор вашего провайдера в системе Android
    public static final String AUTHORITY = "com.example.sro3_db.provider";

    // CONTENT_URI — это "адрес", по которому другие приложения могут обращаться к вашим данным
    // Формат: content://com.example.sro3_db.provider/todos
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/todos");

    // Вызывается системой при запуске приложения для инициализации провайдера
    @Override
    public boolean onCreate() {
        // Создаем экземпляр помощника БД, используя контекст провайдера
        dbHelper = new DBHelper(getContext());
        return true; // Возвращаем true, если провайдер успешно загружен
    }

    // Метод для чтения данных (аналог SELECT в SQL)
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Обращаемся к БД и возвращаем курсор с результатами запроса
        return dbHelper.getReadableDatabase().query(
                DBHelper.TABLE_NAME, // Имя таблицы
                projection,          // Список колонок, которые нужно вернуть (null — все)
                selection,           // Условие фильтрации (WHERE)
                selectionArgs,      // Аргументы для условия фильтрации
                null,                // Группировка (GROUP BY)
                null,                // Условие для групп (HAVING)
                sortOrder            // Сортировка (ORDER BY)
        );
    }

    // Метод для добавления новых данных (аналог INSERT в SQL)
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Вставляем данные в таблицу и получаем ID новой строки
        long id = dbHelper.getWritableDatabase().insert(DBHelper.TABLE_NAME, null, values);
        // Возвращаем URI, указывающий на конкретную новую запись (например, .../todos/5)
        return Uri.withAppendedPath(CONTENT_URI, String.valueOf(id));
    }

    // Метод для удаления данных (аналог DELETE в SQL)
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Удаляем строки, соответствующие условию, и возвращаем количество удаленных строк
        return dbHelper.getWritableDatabase().delete(DBHelper.TABLE_NAME, selection, selectionArgs);
    }

    // Метод для обновления данных (аналог UPDATE в SQL)
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // Обновляем данные и возвращаем количество измененных строк
        return dbHelper.getWritableDatabase().update(DBHelper.TABLE_NAME, values, selection, selectionArgs);
    }

    // Возвращает MIME-тип данных, с которыми работает провайдер
    @Override
    public String getType(Uri uri) {
        // "vnd.android.cursor.dir" означает, что URI возвращает набор данных (список)
        return "vnd.android.cursor.dir/" + AUTHORITY + ".todos";
    }
}