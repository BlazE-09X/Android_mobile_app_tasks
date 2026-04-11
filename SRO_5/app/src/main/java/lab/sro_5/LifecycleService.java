package lab.sro_5;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class LifecycleService extends Service {
    private final String TAG = "LifecycleService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: Сервис создан");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: Сервис запущен");
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: Сервис привязан");
        return new Binder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Сервис уничтожен");
    }
}