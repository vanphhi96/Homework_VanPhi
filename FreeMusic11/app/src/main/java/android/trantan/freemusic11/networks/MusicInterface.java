package android.trantan.freemusic11.networks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Trần_Tân on 15/11/2017.
 */

public interface MusicInterface {
    @GET("api")
    Call<MusicTypesResponseJSON> getMusicType();
    @GET("https://itunes.apple.com/us/rss/topsongs/limit=50/genre={id}/explicit=true/json")
    Call<TopSongResponseJSON>getTopSong(@Path("id")String id);
}
