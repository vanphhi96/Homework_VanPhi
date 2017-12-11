package android.trantan.freemusic11.event;

import android.trantan.freemusic11.database.DowloadSongModel;

/**
 * Created by vanph on 08/12/2017.
 */

public class OnClickFileDowloadEvent {
    public DowloadSongModel dowloadSongModel;
    public OnClickFileDowloadEvent(DowloadSongModel dowloadSongModel)
    {
        this.dowloadSongModel = dowloadSongModel;
    }
}
