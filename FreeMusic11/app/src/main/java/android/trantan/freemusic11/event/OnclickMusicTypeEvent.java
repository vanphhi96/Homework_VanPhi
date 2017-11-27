package android.trantan.freemusic11.event;

import android.trantan.freemusic11.database.MusicTypeModel;

/**
 * Created by vanph on 22/11/2017.
 */

public class OnclickMusicTypeEvent {
    public MusicTypeModel musicTypeModel;

    public OnclickMusicTypeEvent(MusicTypeModel musicTypeModel) {
        this.musicTypeModel = musicTypeModel;
    }
}
