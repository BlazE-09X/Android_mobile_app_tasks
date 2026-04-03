// Указывает пакет, в котором находится этот класс
package lab11.media.camera.image;

// Импорт стандартных классов Android для работы с Intent и жизненным циклом Activity
import android.content.Intent; // Класс для запуска новых Activity
import android.os.Bundle; // Для сохранения состояния экрана
import android.widget.ImageButton; // Виджет кнопки-картинки

// Импорт компонентов для поддержки современного дизайна и системных отступов
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Главный класс приложения
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Включает режим "от края до края", чтобы контент мог заходить под системные панели
        EdgeToEdge.enable(this);

        // Устанавливает главный макет меню (с кнопками управления)
        setContentView(R.layout.activity_main);

        // Поиск кнопок в XML-файле по их уникальным ID
        ImageButton btnCamera = findViewById(R.id.btn_camera);
        ImageButton btnMedia = findViewById(R.id.btn_play);
        ImageButton btnGallery = findViewById(R.id.btn_image);

        // Обработка нажатия на кнопку "Камера"
        btnCamera.setOnClickListener(v -> {
            // Создаем намерение (Intent): откуда (MainActivity) -> куда (CameraActivity)
            Intent intent = new Intent(MainActivity.this, CameraActivity.class);
            startActivity(intent); // Запускаем экран камеры
        });

        // Обработка нажатия на кнопку "Галерея"
        btnGallery.setOnClickListener(v -> {
            // Краткая запись создания Intent (используем this как текущий контекст)
            Intent intent = new Intent(this, GalleryActivity.class);
            startActivity(intent); // Запускаем экран галереи
        });

        // Обработка нажатия на кнопку "Медиаплеер"
        btnMedia.setOnClickListener(v -> {
            // Переход к экрану проигрывателя
            Intent intent = new Intent(this, MediaActivity.class);
            startActivity(intent);
        });
    }
}