package lab.sro_5;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import androidx.appcompat.app.AppCompatActivity;

public class ServiceActivity extends AppCompatActivity {


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Intent intent = new Intent(this, LifecycleService.class);


        findViewById(R.id.btnStart).setOnClickListener(v -> startService(intent));


        findViewById(R.id.btnBind).setOnClickListener(v -> {
            bindService(intent, connection, BIND_AUTO_CREATE);
        });

        findViewById(R.id.btnStop).setOnClickListener(v -> {
            stopService(intent);

            try {
                unbindService(connection);
            } catch (IllegalArgumentException e) {
            }
        });
    }
}