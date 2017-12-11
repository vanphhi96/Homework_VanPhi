package android.trantan.freemusic11.notification;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.trantan.freemusic11.utils.MusicHandler;
import android.util.Log;

/**
 * Created by vanph on 09/12/2017.
 */

public class MusicService extends Service {
    public static String TAG = MusicService.class.toString();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MusicHandler.playPuase();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.d(TAG,"onTaskRemoved: ");
        MusicNotification.builder.setOngoing(false);
        MusicNotification.notificationManager.cancelAll();
    }
}
