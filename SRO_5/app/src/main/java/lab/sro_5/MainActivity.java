package lab.sro_5;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnLifecycle).setOnClickListener(v ->
                startActivity(new Intent(this, ServiceActivity.class)));

        findViewById(R.id.btnNotif).setOnClickListener(v ->
                startActivity(new Intent(this, NotificationActivity.class)));

        findViewById(R.id.btnPlayer).setOnClickListener(v ->
                startActivity(new Intent(this, PlayerActivity.class)));

        findViewById(R.id.btnMsg).setOnClickListener(v ->
                startActivity(new Intent(this, MessageActivity.class)));
    }
}