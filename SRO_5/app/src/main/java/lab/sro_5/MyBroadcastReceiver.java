package lab.sro_5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("msg");
        Toast.makeText(context, "Принято: " + message, Toast.LENGTH_LONG).show();
    }
}