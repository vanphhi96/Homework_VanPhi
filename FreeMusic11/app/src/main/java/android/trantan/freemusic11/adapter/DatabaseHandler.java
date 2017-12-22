package android.trantan.freemusic11.adapter;

import android.trantan.freemusic11.database.MusicTypeModel;

import java.util.List;

import io.realm.Realm;

/**
 * Created by vanph on 16/12/2017.
 */

public class DatabaseHandler {
    private static Realm realm = Realm.getDefaultInstance();
    public static void addMusicType(MusicTypeModel musicTypeModel)
    {
        realm.beginTransaction();
        realm.copyToRealm(musicTypeModel);
        realm.commitTransaction();
    }
    public static List<MusicTypeModel> getMusicTypeModels()
    {
        return realm.where(MusicTypeModel.class).findAll();
    }
    public static void updateFavourite(MusicTypeModel musicTypeModel){
        realm.beginTransaction();
        musicTypeModel.isFavourite=!musicTypeModel.isFavourite;
        realm.commitTransaction();
    }
    public static List<MusicTypeModel> getFavourite()
    {
        return  realm.where(MusicTypeModel.class)
                .equalTo("isFavourite",true).findAll();
    }
}
