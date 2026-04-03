// Указывает пакет проекта
package com.example.sro3_db;

// Импорты для работы с БД и интерфейсом
import android.database.Cursor; // Объект с данными из БД
import android.view.LayoutInflater; // Нужен для "раздувания" (создания) View из XML
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

// Основной класс адаптера, который управляет ViewHolder-ами
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    // Хранилище данных — курсор, указывающий на результат запроса к БД
    private Cursor cursor;

    // Конструктор: принимает начальный курсор при создании адаптера
    public TodoAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    // Метод для обновления данных в списке без перезапуска всей Activity
    public void swapCursor(Cursor newCursor) {
        // Если старый курсор существует, закрываем его для освобождения памяти
        if (cursor != null) cursor.close();
        // Заменяем старый курсор на новый
        cursor = newCursor;
        // Сообщаем списку, что данные изменились и нужно перерисовать элементы
        notifyDataSetChanged();
    }

    // Создает новый пустой "контейнер" (ViewHolder) для одной строки списка
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Превращаем XML-файл item_todo в живой объект View
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        // Оборачиваем эту View в наш класс ViewHolder
        return new ViewHolder(v);
    }

    // Заполняет созданный ViewHolder конкретными данными из курсора
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Перемещаем "курсор" (указатель) на нужную строку в таблице по позиции элемента
        if (cursor.moveToPosition(position)) {
            // Извлекаем данные из колонок по их именам и устанавливаем в TextView
            // getColumnIndexOrThrow находит индекс колонки по её названию из DBHelper
            holder.title.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_TITLE)));
            holder.deadline.setText("Дедлайн: " + cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_DEADLINE)));
            holder.category.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_CATEGORY)));
        }
    }

    // Возвращает общее количество элементов в списке
    @Override
    public int getItemCount() {
        // Если курсор не пуст — возвращаем количество строк, иначе 0
        return cursor != null ? cursor.getCount() : 0;
    }

    // Внутренний класс, который хранит ссылки на виджеты внутри одного элемента списка
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, deadline, category;

        // Конструктор находит View по ID один раз, чтобы не делать этого постоянно в onBind
        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            deadline = itemView.findViewById(R.id.tvDeadline);
            category = itemView.findViewById(R.id.tvCategory);
        }
    }
}