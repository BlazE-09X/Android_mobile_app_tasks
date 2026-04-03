package lab11.media.camera.sro_4;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnTask1).setOnClickListener(v ->
                startActivity(new Intent(this, FormActivity.class)));

        findViewById(R.id.btnTask2).setOnClickListener(v ->
                startActivity(new Intent(this, CameraActivity.class)));

        findViewById(R.id.btnTask3).setOnClickListener(v ->
                startActivity(new Intent(this, GameActivity.class)));
    }
}