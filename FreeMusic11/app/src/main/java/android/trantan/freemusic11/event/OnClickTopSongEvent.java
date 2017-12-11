package android.trantan.freemusic11.event;

import android.trantan.freemusic11.database.TopSongModel;

/**
 * Created by vanph on 29/11/2017.
 */

public class OnClickTopSongEvent {
    public TopSongModel topSongModel;
    public OnClickTopSongEvent(TopSongModel topSongModel)
    {
        this.topSongModel = topSongModel;
    }
}
