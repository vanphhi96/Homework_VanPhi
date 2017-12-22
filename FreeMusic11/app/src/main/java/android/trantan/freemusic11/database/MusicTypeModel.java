package android.trantan.freemusic11.database;

import io.realm.RealmObject;

/**
 * Created by vanph on 18/11/2017.
 */

public class MusicTypeModel extends RealmObject {
    public String key;
    public String id;
    public int imageID;
    public boolean isFavourite;
}
