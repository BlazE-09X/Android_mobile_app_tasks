// Указывает пакет проекта
package lab11.media.camera.image;

// Импорт классов для работы с мультимедиа и графическим интерфейсом
import android.media.MediaPlayer; // Основной класс для воспроизведения аудио/видео
import android.os.Bundle;
import android.view.SurfaceHolder; // Интерфейс для управления поверхностью отрисовки видео
import android.view.SurfaceView;   // Виджет, на котором будет отображаться видеоряд
import android.view.View;
import android.widget.CheckBox;    // Флажок для настройки повтора (зацикливания)
import android.widget.EditText;    // Поле для ввода пути к файлу

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

// Activity реализует Callback, чтобы знать, когда экран готов принять видеопоток
public class MediaActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    // Объявление объектов плеера и элементов управления
    MediaPlayer mediaPlayer;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    EditText etPath;
    CheckBox cbLoop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Установка макета плеера
        setContentView(R.layout.media_activity);

        // Инициализация виджетов из XML
        etPath = findViewById(R.id.etPath);
        cbLoop = findViewById(R.id.loop);
        surfaceView = findViewById(R.id.surfaceView);

        // Получаем управление поверхностью (Holder)
        surfaceHolder = surfaceView.getHolder();
        // Подписываемся на события жизненного цикла поверхности (создана/изменена/удалена)
        surfaceHolder.addCallback(this);
    }

    // Метод, вызываемый при нажатии на кнопку "Старт" (прописано в XML через android:onClick)
    public void onClickStart(View v) {
        try {
            // Если плеер уже был запущен, освобождаем его ресурсы перед новым запуском
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }

            // Создаем новый экземпляр плеера
            mediaPlayer = new MediaPlayer();

            // Указываем источник данных (путь к файлу или URL из EditText)
            mediaPlayer.setDataSource(etPath.getText().toString());

            // Привязываем вывод видео к нашему SurfaceView через holder
            mediaPlayer.setDisplay(surfaceHolder);

            // Устанавливаем режим повтора в зависимости от состояния CheckBox
            mediaPlayer.setLooping(cbLoop.isChecked());

            // Подготавливаем плеер к работе (синхронно считываем начало файла)
            mediaPlayer.prepare();

            // Начинаем воспроизведение
            mediaPlayer.start();
        } catch (Exception e) {
            // Вывод ошибки в логи, если путь неверный или файл поврежден
            e.printStackTrace();
        }
    }

    // Общий метод для кнопок управления (Пауза, Продолжить, Стоп)
    public void onClick(View v) {
        // Если плеер не инициализирован, ничего не делаем
        if (mediaPlayer == null) return;

        int id = v.getId();
        if (id == R.id.btn_pause) {
            // Если видео играет — ставим на паузу
            if (mediaPlayer.isPlaying()) mediaPlayer.pause();
        } else if (id == R.id.btn_resume) {
            // Если видео на паузе — продолжаем играть
            if (!mediaPlayer.isPlaying()) mediaPlayer.start();
        } else if (id == R.id.btn_stop) {
            // Полная остановка воспроизведения
            mediaPlayer.stop();
        }
    }

    // Обязательные методы интерфейса SurfaceHolder.Callback
    @Override public void surfaceCreated(SurfaceHolder holder) {
        // Вызывается, когда область для рисования видео готова
    }

    @Override public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Вызывается при изменении размеров экрана
    }

    @Override public void surfaceDestroyed(SurfaceHolder holder) {
        // Когда пользователь закрывает экран, обязательно освобождаем плеер
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}