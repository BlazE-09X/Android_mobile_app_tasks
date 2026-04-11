package lab.sro_5;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MessageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        findViewById(R.id.btnSend).setOnClickListener(v -> {
            Intent intent = new Intent("com.example.MY_NOTIFICATION_ACTION");
            intent.putExtra("msg", "Привет из Broadcast!");
            sendBroadcast(intent);
        });
    }
}