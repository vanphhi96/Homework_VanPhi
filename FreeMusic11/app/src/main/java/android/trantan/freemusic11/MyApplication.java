package android.trantan.freemusic11;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by vanph on 16/12/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
