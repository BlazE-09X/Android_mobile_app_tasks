package lab.sro_5;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        Intent intent = new Intent(this, MyForegroundService.class);


        findViewById(R.id.btnShowNotif).setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent);
            } else {
                startService(intent);
            }
        });


        findViewById(R.id.btnStopNotif).setOnClickListener(v -> {
            stopService(intent);
        });
    }
}