package lab11.media.camera.sro_4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;

public class CameraActivity extends AppCompatActivity {
    ImageView ivProfile;
    // Объект для хранения небольших данных (в данном случае — пути к фото)
    SharedPreferences pref;
    // Константа-идентификатор для запроса на камеру
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ivProfile = findViewById(R.id.ivProfile);
        // Настройка SharedPreferences с названием "StudentPrefs"
        // MODE_PRIVATE означает, что данные доступны только нашему приложению.
        pref = getSharedPreferences("StudentPrefs", MODE_PRIVATE);

        // Обработка клика по кнопке "Сделать фото"
        findViewById(R.id.btnCapture).setOnClickListener(v -> {
            // Intent (Намерение) — это объект, через который мы общаемся с Android.
            // Здесь мы просим систему найти любое приложение, умеющее делать фото (ACTION_IMAGE_CAPTURE).
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            // Запускаем это намерение. Система откроет камеру, а после закрытия камеры
            // вернет результат обратно в наш метод onActivityResult.
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        });

        // Сразу при загрузке экрана проверяем, не сохраняли ли мы фото раньше
        loadPhoto();
    }


    //Когда приложение камеры закрывается, управление возвращается в приложение через этот метод.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 1. Проверяем, что это ответ на наш запрос (REQUEST_IMAGE_CAPTURE)
        // 2. Проверяем, что пользователь нажал "ОК" (галочку), а не просто закрыл камеру (RESULT_OK)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // В объекте data лежат дополнительные данные (extras)
            Bundle extras = data.getExtras();
            // По ключу "data" Android возвращает объект Bitmap.
            // Важно: это только превью (миниатюра), её качество невысокое.
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivProfile.setImageBitmap(imageBitmap);
            savePhoto(imageBitmap);
        }
    }

    private void savePhoto(Bitmap bitmap) {
        // Сохраняем картинку в файл и путь в Prefs
        try {
            File file = new File(getExternalFilesDir(null), "profile.jpg");
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

            // Теперь самое важное: файл сохранен, но нам нужно запомнить, ГДЕ он лежит.
            // Пишем полный путь к файлу (getAbsolutePath) в SharedPreferences.
            pref.edit().putString("photo_path", file.getAbsolutePath()).apply();

        } catch (Exception e) { e.printStackTrace(); }
    }

    private void loadPhoto() {
        String path = pref.getString("photo_path", null);
        if (path != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            ivProfile.setImageBitmap(bitmap);
        }
    }
}