// Указывает пакет, где находится этот класс
package lab11.media.camera.image;

// Импорт необходимых классов Android для работы с UI и файлами
import android.net.Uri; // Класс для работы с путями к файлам (унифицированный идентификатор ресурса)
import android.os.Bundle; // Для передачи состояния Activity
import android.widget.Button; // Виджет кнопки
import android.widget.ImageView; // Виджет для отображения картинки
import android.widget.TextView; // Виджет для отображения текста

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File; // Класс для работы с файловой системой Java

// Класс Activity для отображения галереи снимков
public class GalleryActivity extends AppCompatActivity {

    // Объявление переменных интерфейса и данных
    ImageView imageView; // Поле для отображения фото
    TextView tvCount;    // Текстовое поле для счетчика (например, "1 / 5")
    File[] imageFiles;   // Массив объектов File, который будет хранить список всех фото
    int currentIndex = 0; // Индекс текущей фотографии, которую мы видим на экране

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Установка макета галереи
        setContentView(R.layout.gallery_activity);

        // Связываем переменные с элементами из XML-файла
        imageView = findViewById(R.id.image_gallery);
        tvCount = findViewById(R.id.count);
        Button btnBack = findViewById(R.id.btn_back);
        Button btnForward = findViewById(R.id.btn_next);

        // 1. Получаем доступ к папке, куда CameraActivity сохраняла снимки
        // getExternalFilesDir(null) возвращает путь к папке приложения во внешнем хранилище
        File dir = getExternalFilesDir(null);

        // listFiles фильтрует файлы: берем только те, которые заканчиваются на ".jpg"
        imageFiles = dir.listFiles((d, name) -> name.endsWith(".jpg"));

        // 2. Проверяем, удалось ли найти файлы и не пуста ли папка
        if (imageFiles != null && imageFiles.length > 0) {
            updateDisplay(); // Если фото есть, показываем первое (индекс 0)
        } else {
            // Если фото нет, выводим сообщение в счетчик
            tvCount.setText("Галерея пуста");
        }

        // Обработка нажатия на кнопку "Вперед"
        btnForward.setOnClickListener(v -> {
            // Проверяем: существует ли массив и не достигли ли мы последнего элемента
            if (imageFiles != null && currentIndex < imageFiles.length - 1) {
                currentIndex++; // Переходим к следующему индексу
                updateDisplay(); // Обновляем картинку и текст на экране
            }
        });

        // Обработка нажатия на кнопку "Назад"
        btnBack.setOnClickListener(v -> {
            // Проверяем: существует ли массив и не находимся ли мы на самом первом элементе (0)
            if (imageFiles != null && currentIndex > 0) {
                currentIndex--; // Уменьшаем индекс
                updateDisplay(); // Обновляем экран
            }
        });
    }

    // Вспомогательный метод для обновления интерфейса
    private void updateDisplay() {
        // Преобразуем объект File в Uri и устанавливаем его в ImageView
        // Это самый простой способ загрузить картинку из файла в Android
        imageView.setImageURI(Uri.fromFile(imageFiles[currentIndex]));

        // Обновляем текстовый счетчик. К индексу прибавляем 1, так как для пользователя счет идет с 1, а не с 0.
        tvCount.setText((currentIndex + 1) + " / " + imageFiles.length);
    }
}