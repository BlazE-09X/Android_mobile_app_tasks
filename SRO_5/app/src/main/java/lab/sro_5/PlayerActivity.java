package lab.sro_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        findViewById(R.id.btnToast).setOnClickListener(v ->
                Toast.makeText(this, "Это простое сообщение!", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnPlay).setOnClickListener(v ->
                startService(new Intent(this, PlayService.class)));

        findViewById(R.id.btnStopMusic).setOnClickListener(v ->
                stopService(new Intent(this, PlayService.class)));
    }
}