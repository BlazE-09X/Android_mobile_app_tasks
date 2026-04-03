package lab11.media.camera.sro_4;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {
    EditText etName, etDate;
    Spinner spGroup;
    // Выпадающий список (группа)
    SeekBar sbAge;
    // Ползунок (выбор возраста)
    SharedPreferences pref;
    // Хранилище для сохранения данных формы

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etName = findViewById(R.id.etName);
        spGroup = findViewById(R.id.spGroup);
        sbAge = findViewById(R.id.sbAge);
        etDate = findViewById(R.id.etDate);

        pref = getSharedPreferences("StudentPrefs", MODE_PRIVATE);

        // Календарь при клике на поле даты
        etDate.setOnClickListener(v -> showDatePicker());

        loadData();
        TextView tvAgeLabel = findViewById(R.id.tvAgeLabel);

        // Настройка слушателя для ползунка (SeekBar)
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // progress - это число, на котором сейчас стоит ползунок
                tvAgeLabel.setText("Возраст: " + progress);
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void showDatePicker() {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, month, day) -> {
            etDate.setText(day + "/" + (month + 1) + "/" + year);
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void saveData() {
        pref.edit()
                // Открываем редактор (edit()), записываем данные и применяем изменения (apply())
                .putString("name", etName.getText().toString())
                .putInt("group", spGroup.getSelectedItemPosition())
                .putInt("age", sbAge.getProgress())
                .putString("date", etDate.getText().toString())
                .apply();
    }

    private void loadData() {
        // Если ключа в памяти нет, подставится значение по умолчанию (пустая строка или 0)
        etName.setText(pref.getString("name", ""));
        spGroup.setSelection(pref.getInt("group", 0));
        sbAge.setProgress(pref.getInt("age", 18));
        etDate.setText(pref.getString("date", ""));
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Пользователь не нажимал кнопку "Сохранить",
        // но записывается всё, что он успел ввести.
        saveData(); // Автосохранение при выходе из экрана
    }
}
