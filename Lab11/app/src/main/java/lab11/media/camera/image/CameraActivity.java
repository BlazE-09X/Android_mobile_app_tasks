// Указывает пакет, где лежит файл (в папке lab11)
package lab11.media.camera.image;

// Импорт необходимых классов Android для работы с оборудованием и интерфейсом
import android.hardware.Camera; // Основной класс для управления камерой (Deprecated, но рабочий)
import android.os.Bundle; // Для передачи данных состояния Activity
import android.view.SurfaceHolder; // Интерфейс для управления поверхностью отрисовки
import android.view.SurfaceView; // Виджет, который отображает поток видео с камеры
import android.widget.ImageButton; // Кнопка с изображением (для иконки затвора)

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

// Класс Activity, который сам реализует SurfaceHolder.Callback для отслеживания состояния экрана
public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private Camera camera; // Объект самой камеры
    private SurfaceHolder surfaceHolder; // "Держатель" поверхности, через который мы управляем отрисовкой
    private SurfaceView surfaceView; // Ссылка на сам элемент в разметке

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Установка макета камеры (в нем должен быть SurfaceView и ImageButton)
        setContentView(R.layout.camera_activity);

        // Находим SurfaceView в XML
        surfaceView = findViewById(R.id.surfaceViewCamera);
        // Получаем холдер (управленец) для этой поверхности
        surfaceHolder = surfaceView.getHolder();
        // Подписываем текущий класс (this) на события: создание, изменение или удаление экрана
        surfaceHolder.addCallback(this);

        // Находим кнопку съемки
        ImageButton btnCapture = findViewById(R.id.btn_camera_click);
        btnCapture.setOnClickListener(v -> {
            // Если камера инициализирована и готова
            if (camera != null) {
                // takePicture делает снимок. Аргументы: затвор, сырые данные, JPEG-данные
                camera.takePicture(null, null, (data, cam) -> {
                    try {
                        // Генерируем уникальное имя файла на основе текущего времени
                        String fileName = "IMG_" + System.currentTimeMillis() + ".jpg";
                        // Создаем файл во внутреннем хранилище приложения
                        java.io.File file = new java.io.File(getExternalFilesDir(null), fileName);

                        // Поток для записи байтов картинки в файл
                        java.io.FileOutputStream fos = new java.io.FileOutputStream(file);
                        fos.write(data); // Записываем массив байтов (data)
                        fos.close(); // Закрываем файл

                        // Показываем уведомление об успехе
                        android.widget.Toast.makeText(this, "Сохранено: " + fileName, android.widget.Toast.LENGTH_SHORT).show();

                        // После снимка предпросмотр замирает, запускаем его снова
                        cam.startPreview();
                    } catch (Exception e) {
                        e.printStackTrace(); // Вывод ошибки в логи, если запись не удалась
                    }
                });
            }
        });
    }

    // Вызывается системой, когда поверхность (SurfaceView) готова к работе
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            // Пытаемся открыть стандартную заднюю камеру
            camera = Camera.open();
            Camera.Parameters parameters = camera.getParameters();
            // Поворачиваем сохраняемый файл JPEG на 90 градусов
            parameters.setRotation(90);
            camera.setParameters(parameters);

            // Поворачиваем только визуальный предпросмотр на экране для портретного режима
            camera.setDisplayOrientation(90);
            // Привязываем камеру к нашему "холсту" (surfaceHolder)
            camera.setPreviewDisplay(holder);
            // Включаем передачу видеосигнала на экран
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Вызывается, если размеры экрана изменились (например, при смене ориентации)
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Здесь можно пересчитывать соотношение сторон (Aspect Ratio)
    }

    // Вызывается, когда пользователь закрывает экран или сворачивает приложение
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // Обязательно освобождаем камеру, иначе другие приложения не смогут её открыть
        if (camera != null) {
            camera.stopPreview(); // Останавливаем видеопоток
            camera.release();     // Полностью освобождаем ресурс камеры
            camera = null;        // Зануляем ссылку
        }
    }
}